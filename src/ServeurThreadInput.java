import java.net.Socket;
import java.util.Scanner;

public class ServeurThreadInput implements Runnable{
    private GestionMessage gestionMessage;
    private GestionUtilisateurs gestionUtilisateurs;

    public ServeurThreadInput(GestionMessage gestionMessage, GestionUtilisateurs gestionUtilisateurs) {
        this.gestionMessage = gestionMessage;
        this.gestionUtilisateurs = gestionUtilisateurs;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String commande = scanner.nextLine();
                if (commande.split(" ")[0].equals("/delete") && commande.split(" ").length == 2 && commande.split(" ")[1].matches("[0-9]+")) {
                    int id = Integer.parseInt(commande.split(" ")[1]);
                    if (this.gestionMessage.deleteS(id)) {
                        System.out.println("Message supprimé");
                    }
                    else {
                        System.out.println("L'id ne correspond à aucun message");
                    }
                }
                else {
                    System.out.println("Commande inconnue");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Erreur");
        }
    }
}
