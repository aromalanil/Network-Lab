import java.io.Console;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SMTP {
    public static void main(String[] args) {

        Console console = System.console();

        String senderEmail = console.readLine("Enter senders mail id: ");
        String senderPassword = new String(console.readPassword("Enter mail password: "));
        String receiverEmail = console.readLine("Enter receivers mail id: ");

        // Get the session object
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.prot", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
            message.setSubject("Network Lab Test");
            message.setText("This mail was send using SMTP program build with Java");

            Transport.send(message);

            System.out.println("Message sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}