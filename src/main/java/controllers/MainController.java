package controllers;

import helpers.OpenNewWindow;
import helpers.ShowLogo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import mailControllers.CheckingMessages;
import mailControllers.SendEmail;
import properties.Service;
import properties.User;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * @Author: Sylwester Gawroński
 */
public class MainController implements Initializable {
    private OpenNewWindow openNewWindow = new OpenNewWindow();
   private SendEmail sendEmail = new SendEmail();
   private CheckingMessages checkingMessages = new CheckingMessages();
   private User user = new User();
   private Service service = new Service();

    @FXML
    TextField textFieldAddres;
    @FXML
    TextField textFieldTitle;
    @FXML
    TextArea textAreaMessage;
    @FXML
    Button buttonSend;
    @FXML
    ImageView imageViewIcon;
    @FXML
    Label labelCount;
    @FXML
    ListView listViewMessages;
    @FXML
    ProgressIndicator progress;
    @FXML
    MenuItem menuIntemAbout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViewIcon.setImage(ShowLogo.getLogo());
        checkingMessages.checkMail(service.getHostPOP(),service.getMailStoreType(),user.getFROM(),user.getPASSWORD());
        labelCount.setText(String.valueOf(checkingMessages.getMessagesCout()));
        listViewMessages.getItems().addAll(checkingMessages.getListMessage().keySet());
    }


    @FXML
    private void sendMessage(){
        sendEmail.sendEmail(textFieldAddres.getText(),textFieldTitle.getText(),textAreaMessage.getText());

    }
    @FXML
    private void about(){
         openNewWindow.openWindow("aboutEmailSenderWindow.fxml","About EmailSender");

    }
}
