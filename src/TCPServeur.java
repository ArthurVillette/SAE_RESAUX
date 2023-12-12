import java.io.*;import java.net.*;import java.util.*;
public class TCPServeur {
public static void main(String [] args) throws IOException {
DatagramSocket socket = new DatagramSocket(4445);
byte[] buf = new byte[256];
// receive request
DatagramPacket packet = new DatagramPacket(buf, buf.length);
socket.receive(packet);
// response
String dString = new Date().toString();
buf = dString.getBytes();
// send the response to the client at ”address” and ”port”
InetAddress address = packet.getAddress();
int port = packet.getPort();
packet = new DatagramPacket(buf, buf.length, address, port);
socket.send(packet);
while (true) {
    socket.receive(packet);
    String received = new String(packet.getData(), 0, packet.getLength());
    System.out.println(received);
    
}
// socket.close();
}} 
