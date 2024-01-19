import java.io.*; 
import java.net.*; 
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServeurThread implements Runnable {
        private Socket clientSocket;
        private GestionUtilisateurs gestionUtilisateurs;
        private GestionMessage gestionMessage;
        private Utilisateur utilisateur;
        private ConcurrentHashMap<Socket, List<Message>> messages;

        public ServeurThread(Socket socket, GestionUtilisateurs gestionUtilisateurs, GestionMessage gestionMessage, ConcurrentHashMap<Socket, List<Message>> messages) {
            this.clientSocket = socket;
            this.gestionUtilisateurs = gestionUtilisateurs;
            this.gestionMessage = gestionMessage;
            this.utilisateur = null;
            this.messages = messages;
        }

        public void run() {
            try {
                InputStreamReader stream = new InputStreamReader(this.clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(stream);
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                while (this.utilisateur == null) {
                    output.println(GestionCommande.commandeToJson("Quel est votre nom ?"));
                    String nom = GestionCommande.jsonToCommande(reader.readLine()).getCommande();
                    if (gestionUtilisateurs.userExists(nom)) {
                        output.println(GestionCommande.commandeToJson("Quel est votre mot de passe ?"));
                        String mdp = GestionCommande.jsonToCommande(reader.readLine()).getCommande();
                        if (this.gestionUtilisateurs.getUtilisateur(nom).getMdp().equals(mdp)){
                            this.utilisateur = gestionUtilisateurs.getUtilisateur(nom);
                            output.println(GestionCommande.commandeToJson("Bienvenue " + this.utilisateur.getNom()));
                        }
                        else {
                            output.println(GestionCommande.commandeToJson("Mot de passe incorrect"));
                        }
                    }
                    else {
                        output.println(GestionCommande.commandeToJson("Le nom n'existe pas un utilisateur va être crée, quel mot de passe voulez-vous utiliser"));
                        String mdp = GestionCommande.jsonToCommande(reader.readLine()).getCommande();
                        gestionUtilisateurs.addUser(nom,mdp);
                        this.utilisateur = gestionUtilisateurs.getUtilisateur(nom);
                        output.println(GestionCommande.commandeToJson("Utilisateur créé"));
                    }
                }
                new Thread(new ServeurThreadEnvoie(this.clientSocket, this.gestionMessage, this.messages)).start();
                String message;
                while ((message = GestionCommande.jsonToCommande(reader.readLine()).getCommande()) != null) {
                    Message messageObjet = new Message(this.gestionMessage.getMaximumId()+1, this.utilisateur.getNom(), message);
                    this.gestionMessage.addMessage(messageObjet);
                    System.out.println(messageObjet);
                    for (List<Message> liste : this.messages.values()) {
                        liste.add(messageObjet);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
