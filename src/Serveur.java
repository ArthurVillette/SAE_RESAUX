import java.io.*; 
import java.net.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList; 

public class Serveur {
    public static void main(String [] args) throws IOException {
        try{
            System.out.println(System.getProperty("java.class.path"));
            ServerSocket serverSocket = new ServerSocket(4445);
            ConcurrentHashMap<Socket, List<Message>> messages = new ConcurrentHashMap<>();
            ConnexionMySQL connexionMySQL = new ConnexionMySQL();
            connexionMySQL.connecter("sae_reseaux", "romain", "150404");
            System.out.println("Connexion à la base de données réussie");
            GestionUtilisateurs gestionUtilisateurs = new GestionUtilisateurs(connexionMySQL);
            GestionMessage gestionMessage = new GestionMessage(connexionMySQL);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                messages.put(clientSocket, new CopyOnWriteArrayList<>());
                new Thread(new ServeurThread(clientSocket, gestionUtilisateurs, gestionMessage, messages)).start();
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la connexion au serveur");
        }
    }
}