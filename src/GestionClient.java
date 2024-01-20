import java.net.Socket;

/**
 * représente le gestionnaire du client
 */
public class GestionClient {

    private String serverAddress;
    private int serverPort;
    private Socket socket;

    /**
     * Constructeur de la classe GestionClient.
     *
     * @param serverAddress addresse IP du serveur
     * @param serverPort le port du serveur
     */
    public GestionClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * Permet de lancer le client.
     */
    public void lancer() {
        try {
            this.socket = new Socket(serverAddress, serverPort);

            Thread threadRecept = new Thread(new ClientRecept(this, socket));
            Thread threadEnvoie = new Thread(new ClientEnvoie(this, socket));

            threadRecept.start();
            threadEnvoie.start();
        } catch (Exception e) {
            System.out.println("Impossible de se connecter au serveur");
        }
    }

    /**
     *donne la socket du client
     *
     * @return la socket du client
     */
    public Socket getSocket() {
        return this.socket;
    }
}
