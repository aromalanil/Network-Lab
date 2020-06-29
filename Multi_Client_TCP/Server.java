import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.*;

public class Server {

    int port;
    ServerSocket server = null;
    Socket client = null;
    ExecutorService pool = null;
    int clientCount = 0;

    public static void main(String[] args) throws IOException {
        Server serverObj = new Server(4455);
        serverObj.startServer();
    }

    Server(int port) {
        this.port = port;
        pool = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException {

        server = new ServerSocket(4455);
        System.out.println("Server Initialized");
        System.out.println("Any client can stop the server by sending BYE");
        while (true) {
            client = server.accept();
            clientCount++;
            ServerThread runnable = new ServerThread(client, clientCount, this);
            pool.execute(runnable);
        }

    }

    private static class ServerThread implements Runnable {

        Server server = null;
        Socket client = null;
        BufferedReader clientInput;
        PrintStream clientOutput;
        Scanner sc = new Scanner(System.in);
        int id;
        String s;

        ServerThread(Socket client, int count, Server server) throws IOException {

            this.client = client;
            this.server = server;
            this.id = count;
            System.out.println("Connection " + id + "established with client " + client);

            clientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
            clientOutput = new PrintStream(client.getOutputStream());

        }

        @Override
        public void run() {
            int x = 1;
            try {
                while (true) {
                    s = clientInput.readLine();

                    System.out.print("Client(" + id + ") :" + s + "\n");
                    System.out.print("Server : ");
                    s = sc.nextLine();
                    if (s.equalsIgnoreCase("bye")) {
                        clientOutput.println("BYE");
                        x = 0;
                        System.out.println("Connection ended by server");
                        break;
                    }
                    clientOutput.println(s);
                }

                clientInput.close();
                client.close();
                clientOutput.close();
                if (x == 0) {
                    System.out.println("Server cleaning up.");
                    System.exit(0);
                }
            } catch (IOException ex) {
                System.out.println("Error : " + ex);
            }

        }
    }

}