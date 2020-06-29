import java.io.*;
import java.net.*;

class Server {
   public static void main(String args[]) throws Exception {

      DatagramSocket serverSocket = new DatagramSocket(4455);
      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];

      boolean running = true;

      while (running) {

         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         serverSocket.receive(receivePacket);

         String message = new String(receivePacket.getData());
         System.out.println("The message from Client :" + message);

         InetAddress IPAddress = receivePacket.getAddress();
         int port = receivePacket.getPort();

         String capitalizedMessage = message.toUpperCase();
         sendData = capitalizedMessage.getBytes();

         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

         serverSocket.send(sendPacket);

      }
      serverSocket.close();
   }
}
