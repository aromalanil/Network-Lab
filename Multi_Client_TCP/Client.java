
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) throws Exception {

        Socket socket = new Socket("127.0.0.1", 4455);
        BufferedReader inFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream outFromUser = new PrintStream(socket.getOutputStream());
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String message;

        while (true) {
            System.out.print("Client : ");
            message = inFromUser.readLine();
            outFromUser.println(message);
            
            if (message.equalsIgnoreCase("BYE")) {
                System.out.println("Connection ended by client");
                break;
            }
            message = inFromSocket.readLine();
            System.out.print("Server : " + message + "\n");

        }
        socket.close();
        inFromSocket.close();
        outFromUser.close();
        inFromUser.close();
    }

}