import java.net.Socket;

public class GestionClient {

    private String serverAddress;
    private int serverPort;
    private Socket socket;

    public GestionClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
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
