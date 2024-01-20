import java.io.*; 
import java.net.*; 
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * la classe ServeurThread permet de gérer les threads du serveur
 */
public class ServeurThread implements Runnable {
        private Socket clientSocket;
        private GestionUtilisateurs gestionUtilisateurs;
        private GestionMessage gestionMessage;
        private Utilisateur utilisateur;
        private ConcurrentHashMap<Socket, List<Message>> messages;
        /**
         * le constructeur de la classe ServeurThread
         * @param socket la socket du client
         * @param gestionUtilisateurs le gestionnaire des utilisateurs
         * @param gestionMessage le gestionnaire des messages
         * @param messages la liste des messages
         */
        public ServeurThread(Socket socket, GestionUtilisateurs gestionUtilisateurs, GestionMessage gestionMessage, ConcurrentHashMap<Socket, List<Message>> messages) {
            this.clientSocket = socket;
            this.gestionUtilisateurs = gestionUtilisateurs;
            this.gestionMessage = gestionMessage;
            this.utilisateur = null;
            this.messages = messages;
        }
        /**
         * permet de lancer le thread
         */
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
                String reponse;
                while ((reponse = GestionCommande.jsonToCommande(reader.readLine()).getCommande()) != null) {
                    if (reponse.charAt(0) == '/') {
                        if (reponse.split(" ")[0].equals("/like") && reponse.split(" ").length == 2 && reponse.split(" ")[1].matches("[0-9]+")){
                            int id = Integer.parseInt(reponse.split(" ")[1]);
                            if (this.gestionMessage.likeMessage(id, this.utilisateur.getNom())) {
                                output.println(GestionCommande.commandeToJson("Message liké"));
                            }
                            else {
                                output.println(GestionCommande.commandeToJson("L'id n'existe pas"));
                            }
                        }
                        else if (reponse.split(" ")[0].equals("/get_like") && reponse.split(" ").length == 2 && reponse.split(" ")[1].matches("[0-9]+")){
                            int id = Integer.parseInt(reponse.split(" ")[1]);
                            if (this.gestionMessage.getLikes(id) != null) {
                                output.println(GestionCommande.commandeToJson("Le message a "+this.gestionMessage.getLikes(id)+" likes"));
                            }
                            else {
                                output.println(GestionCommande.commandeToJson("L'id n'existe pas"));
                                
                            }
                        }
                        else {
                            output.println(GestionCommande.commandeToJson("Commande inconnue"));
                        }
                    }
                    else {
                        Message messageObjet = new Message(this.gestionMessage.getMaximumId()+1, this.utilisateur.getNom(), reponse);
                        this.gestionMessage.addMessage(messageObjet);
                        System.out.println(messageObjet);
                        for (List<Message> liste : this.messages.values()) {
                            liste.add(messageObjet);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
