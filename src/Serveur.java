import java.io.*; 
import java.net.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList; 

/**
 * la classe Serveur permet de lancer le serveur
 */
public class Serveur {
    public static void main(String [] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4445);
        GestionUtilisateurs gestionUtilisateurs = new GestionUtilisateurs();
        GestionMessage gestionMessage = new GestionMessage();
        ConcurrentHashMap<Socket, List<Message>> messages = new ConcurrentHashMap<>();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            messages.put(clientSocket, new CopyOnWriteArrayList<>());
            new Thread(new ServeurThread(clientSocket, gestionUtilisateurs, gestionMessage, messages)).start();
        }
    }
}