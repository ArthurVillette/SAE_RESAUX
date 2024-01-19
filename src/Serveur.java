import java.io.*; 
import java.net.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList; 

public class Serveur {
    public static void main(String [] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4445);
        GestionUtilisateurs gestionUtilisateurs = new GestionUtilisateurs();
        GestionMessage gestionMessage = new GestionMessage();
        GestionCommande gestionCommande = new GestionCommande();
        ConcurrentHashMap<Socket, List<Message>> messages = new ConcurrentHashMap<>();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            messages.put(clientSocket, new CopyOnWriteArrayList<>());
            new Thread(new ServeurThread(clientSocket, gestionUtilisateurs, gestionMessage, gestionCommande, messages)).start();
        }
    }
}