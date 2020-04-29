package mailControllers;

import com.sun.mail.util.MailSSLSocketFactory;
import controllers.TrayNotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;

// todo ta klasa musi być odtwarzana cyklicznie
public class CheckingMessages {
    private int messagesCout = 0;
    private int messagesNumber;
    private Message[] messages;
    private ObservableList<String> listStrings = FXCollections.<String>observableArrayList();
    private Map<Message, Object> listMessage = new HashMap<Message, Object>();
    //private Map<String,String> dataTest = new HashMap<>();

    private TrayNotificationController trayNotificationController = new TrayNotificationController();

    public void checkMail(String host, String storeType, String user, String password) {
        try {
            // create properties field
            Properties properties = new Properties();

            MailSSLSocketFactory sf = new MailSSLSocketFactory(); // todo musi być to w opcjach, włączone ignoruje błąd SSL
            sf.setTrustAllHosts(true);

            properties.put("mail.pop3s.ssl.trust", "*");
            properties.put("mail.pop3s.ssl.socketFactory", sf);

            properties.put("mail.pop3s.host", host);
            properties.put("mail.pop3s.port", "995");
            properties.put("mail.pop3s.starttls.enable", "true");

            // Setup authentication, get session
            Session emailSession = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    user, password);
                        }
                    });
             emailSession.setDebug(false);

            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect();

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);


            // retrieve the messages from the folder in an array and print it
            messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            //messagesNumber = messages.length;

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                listStrings.add(i,message.getSubject());
                messagesCout = i + 1;

                // read message
               Object content = message.getContent();
               if (content instanceof MimeMultipart){
                   MimeMultipart multipart = (MimeMultipart) content;
                   if (multipart.getCount()>0){
                       BodyPart part = multipart.getBodyPart(0);
                       content = part.getContent();
                       //System.out.println(content);
                       listMessage.put(message,content);
                   }
               }
            }
            if (messagesCout < 0){
                trayNotificationController.viewNotification("Message", "New Messages = "+ messagesCout);
            }else {
                trayNotificationController.viewNotification("Message","There are no new messages");
            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<Message, Object> getListMessage() {
        return listMessage;
    }

    public int getMessagesCout() {
        return messagesCout;
    }

    public Message[] getMessages() {
        return messages;
    }

    public List<String> getListStrings() {
        return listStrings;
    }

    public int getMessagesNumber() {
        return messagesNumber;
    }
}
