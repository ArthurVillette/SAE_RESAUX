/**
 * la classe client permet de lancer le client
 */
public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 4445;
        GestionClient gestionClient = new GestionClient(serverAddress, serverPort);
        gestionClient.lancer();
    }
}
