import java.io.*;import java.net.*;import java.util.*;
import java.util.Scanner;

public class TCPClient {
public static void main(String[] args) throws IOException {
DatagramSocket socket = new DatagramSocket();
byte[] buf = new byte[256];
InetAddress address = InetAddress.getByName("192.168.28.90");
DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
socket.send(packet);
packet = new DatagramPacket(buf, buf.length);
socket.receive(packet);
String received = new String(packet.getData(), 0, packet.getLength());
System.out.println("Date: " + received);
Scanner write= new Scanner(System.in);
System.out.println("entrer votre nom:");
String nom= write.nextLine();
System.out.println("entrer votre message:");
while (true){
    
    String message= write.nextLine();
    String texte= nom+":"+message;
    System.out.println(texte);
    buf = texte.getBytes();
    packet = new DatagramPacket(buf, buf.length, address, 4445);
    socket.send(packet);

}


// socket.close();
}}