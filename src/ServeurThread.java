import java.io.*; 
import java.net.*; 
import java.util.*;

public class ServeurThread implements Runnable {
        private Socket clientSocket;
        private GestionUtilisateurs gestionUtilisateurs;
        private GestionMessage gestionMessage;
        private GestionCommande gestionCommande;
        private Utilisateur utilisateur;

        public ServeurThread(Socket socket, GestionUtilisateurs gestionUtilisateurs, GestionMessage gestionMessage, GestionCommande gestionCommande) {
            this.clientSocket = socket;
            this.gestionUtilisateurs = gestionUtilisateurs;
            this.gestionMessage = gestionMessage;
            this.gestionCommande = gestionCommande;
            this.utilisateur = null;
        }

        public void run() {
            try {
                InputStreamReader stream = new InputStreamReader(this.clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(stream);
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                while (this.utilisateur == null) {
                    output.println(this.gestionCommande.commandeToJson("Quel est votre nom ?"));
                    String nom = this.gestionCommande.jsonToCommande(reader.readLine()).getCommande();
                    if (gestionUtilisateurs.userExists(nom)) {
                        output.println(this.gestionCommande.commandeToJson("Quel est votre mot de passe ?"));
                        String mdp = this.gestionCommande.jsonToCommande(reader.readLine()).getCommande();
                        if (this.gestionUtilisateurs.getUtilisateur(nom).getMdp().equals(mdp)){
                            this.utilisateur = gestionUtilisateurs.getUtilisateur(nom);
                            output.println(this.gestionCommande.commandeToJson("Bienvenue " + this.utilisateur.getNom()));
                        }
                        else {
                            output.println(this.gestionCommande.commandeToJson("Mot de passe incorrect"));
                        }
                    }
                    else {
                        output.println(this.gestionCommande.commandeToJson("Le nom n'existe pas un utilisateur va être crée, quel mot de passe voulez-vous utiliser"));
                        String mdp = this.gestionCommande.jsonToCommande(reader.readLine()).getCommande();
                        gestionUtilisateurs.addUser(nom,mdp);
                        this.utilisateur = gestionUtilisateurs.getUtilisateur(nom);
                        output.println(this.gestionCommande.commandeToJson("Utilisateur créé"));
                    }
                }
                
                String message;
                while ((message = this.gestionCommande.jsonToCommande(reader.readLine()).getCommande()) != null) {
                    Message messageObjet = new Message(this.gestionMessage.getMaximumId()+1, this.utilisateur.getNom(), message);
                    this.gestionMessage.addMessage(messageObjet);
                    System.out.println(messageObjet);
                    output.println(this.gestionMessage.messageToJson(messageObjet));
                }
            } catch (Exception e) {
                System.out.println("Exception caught when trying to listen on port or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
