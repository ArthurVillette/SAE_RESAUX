import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * la classe ServeurThreadEnvoie permet de gérer le thread d'envoie des messages du serveur
 */
public class ServeurThreadEnvoie implements Runnable {
    private ConcurrentHashMap<String, List<Message>> messages;
    private Socket clientSocket;
    private GestionMessage gestionMessage;
    private Utilisateur utilisateur;

    /**
     * le constructeur de la classe ServeurThreadEnvoie
     * 
     * @param clientSocket   le socket du client
     * @param gestionMessage le gestionnaire des messages
     * @param messages       la liste des messages
     */
    public ServeurThreadEnvoie(Socket clientSocket, GestionMessage gestionMessage, ConcurrentHashMap<String, List<Message>> messages, Utilisateur utilisateur) {
        this.clientSocket = clientSocket;
        this.gestionMessage = gestionMessage;
        this.messages = messages;
        this.utilisateur = utilisateur;
    }

    /**
     * permet de lancer le thread
     */
    public void run() {
        try {
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                if (this.messages.get(this.utilisateur.getNom()).size() > 0) {
                    Message message = this.messages.get(this.utilisateur.getNom()).remove(0);
                    output.println(GestionMessage.messageToJson(message));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
