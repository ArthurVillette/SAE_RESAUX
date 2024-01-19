import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEnvoie implements Runnable {
    
    private GestionClient gestionClient;
    private Socket socket;


    public ClientEnvoie(GestionClient gestionClient, Socket socket) {
        this.gestionClient = gestionClient;
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    String text = scanner.nextLine();
                    System.out.print("\033[1A");
                    System.out.print("\033[2K");
                    String commandeJson = GestionCommande.commandeToJson(text);
                    output.println(commandeJson);
                }
                catch(Exception e) {
                    continue;
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Unable to connect to server");
        }
    }
}
