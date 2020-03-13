package mailControllers;

import controllers.TrayNotificationController;
import properties.Service;
import properties.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * @Author: Sylwester Gawroński
 * todo dodać wysyłanie wiadomości razem z plikami
 */
public class SendEmail {
   private TrayNotificationController trayNotificationController = new TrayNotificationController();
   private User user = new User();
   private Service service = new Service();

    public SendEmail sendEmail(String addresnext,String TITLE,String massage) {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.auth", "true");
        Session session = Session.getDefaultInstance(properties);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setSubject(TITLE);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            mimeMessage.setContent(massage, "text/plain; charset=ISO-8859-2");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(addresnext));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            Transport transport = session.getTransport();
            transport.connect(Service.getHost(), Integer.parseInt(Service.getPORT()), user.getFROM(), user.getPASSWORD());
            transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
            trayNotificationController.viewNotification("Succes", "Message send");
        } catch (MessagingException e) {
            e.printStackTrace();
            trayNotificationController.viewNotification("Error", "MessageException "+ e);
        }
        return null;
    }
}
