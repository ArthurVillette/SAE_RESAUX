import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ServeurThreadEnvoie implements Runnable{
    private ConcurrentHashMap<Socket, List<Message>> messages;
    private Socket clientSocket;
    private GestionMessage gestionMessage;

    public ServeurThreadEnvoie(Socket clientSocket, GestionMessage gestionMessage, ConcurrentHashMap<Socket, List<Message>> messages) {
        this.clientSocket = clientSocket;
        this.gestionMessage = gestionMessage;
        this.messages = messages;
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                if (this.messages.get(this.clientSocket).size() > 0) {
                    Message message = this.messages.get(this.clientSocket).remove(0);
                    output.println(this.gestionMessage.messageToJson(message));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
