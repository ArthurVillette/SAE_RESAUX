import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 4445;
        GestionClient gestionClient = new GestionClient(serverAddress, serverPort);
        gestionClient.lancer();
    }
}
