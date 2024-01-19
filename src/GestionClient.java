import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class GestionClient {

    private CopyOnWriteArrayList<Commande> commandesEnAttente;
    private String serverAddress;
    private int serverPort;
    private Socket socket;

    public GestionClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.commandesEnAttente = new CopyOnWriteArrayList<Commande>();
    }

    public void addCommandeEnAttente(Commande commande) {
        this.commandesEnAttente.add(commande);
    }

    public CopyOnWriteArrayList<Commande> getCommandesEnAttente() {
        return this.commandesEnAttente;
    }

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

    public Socket getSocket() {
        return this.socket;
    }
}
