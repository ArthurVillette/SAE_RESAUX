import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientRecept implements Runnable {

    private GestionClient gestionClient;
    private Socket socket;

    public ClientRecept(GestionClient gestionClient, Socket socket) {
        this.gestionClient = gestionClient;
        this.socket = socket;
    }
    
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (true) {
                String reponse = reader.readLine();
                try {
                    Message message = GestionMessage.jsonToMessage(reponse);
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
