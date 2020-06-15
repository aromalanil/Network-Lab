import java.io.*;
import java.net.*;

class Client {

    public static void main(String args[]) throws Exception {

        Socket s = new Socket("127.0.0.1", 3000);

        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        String str, str1;

        while (!(str = kb.readLine()).equals("exit")) {

            dos.writeBytes(str + "\n");

            str1 = br.readLine();

            System.out.println(str1);
        }

        dos.close();
        br.close();
        kb.close();
        s.close();
    }
}