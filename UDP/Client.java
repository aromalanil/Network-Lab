import java.io.*;
import java.net.*;

class Client {
   public static void main(String args[]) throws Exception {

      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];

      String message = inFromUser.readLine();
      sendData = message.getBytes();

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 4455);
      clientSocket.send(sendPacket);

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      
      String receivedMessage = new String(receivePacket.getData());
      System.out.println("The message from Server :" + receivedMessage);

      clientSocket.close();
   }
}
