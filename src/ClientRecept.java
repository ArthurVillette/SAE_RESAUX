import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Represente les threads qui gèrent la réception des messages du serveur par le client.
 */
public class ClientRecept implements Runnable {

    private GestionClient gestionClient;
    private Socket socket;
    private GestionMessage gestionMessage;

    /**
     * Constructeur de la classe ClientRecept.
     * 
     * @param gestionClient le gestionnaire du client.
     * @param socket la socket du client.
     */
    public ClientRecept(GestionClient gestionClient, Socket socket) {
        this.gestionClient = gestionClient;
        this.socket = socket;
        this.gestionMessage = new GestionMessage();
    }
    
    /**
     * Permet de lancer le thread.
     */
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (true) {
                String reponse = reader.readLine();
                try {
                    Message message = gestionMessage.jsonToMessage(reponse);
                    if (message.getNomUtilisateur() == null) {
                        throw new Exception();
                    }
                    System.out.println(message.getNomUtilisateur()+" : "+message.getContent()+" (id : "+message.getId()+" date : "+message.getDate()+")");
                }
                catch(Exception e) {
                    Commande commande = GestionCommande.jsonToCommande(reponse);
                    System.out.println(commande.getCommande());
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Unable to connect to server");
        }
    }
}
