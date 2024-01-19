import java.io.*; 
import java.net.*; 
import java.util.*;

public class Serveur {
    public static void main(String [] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4445);
        GestionUtilisateurs gestionUtilisateurs = new GestionUtilisateurs();
        GestionMessage gestionMessage = new GestionMessage();
        GestionCommande gestionCommande = new GestionCommande();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ServeurThread(clientSocket, gestionUtilisateurs, gestionMessage, gestionCommande)).start();
        }
    }
}