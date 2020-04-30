package controllers;

import helpers.OpenNewWindow;
import helpers.ShowLogo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import mailControllers.CheckingMessages;
import mailControllers.SendEmail;
import org.apache.commons.lang3.StringUtils;
import properties.Service;
import properties.User;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * @Author: Sylwester Gawroński
 */
public class MainController implements Initializable, Runnable {
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
    @FXML
    MenuItem menuItemRefresh;
    @FXML
    TextArea viewTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViewIcon.setImage(ShowLogo.getLogo());
        run();
    }

    @FXML
    private void sendMessage(){
        if (!StringUtils.isBlank(textFieldAddres.getCharacters())||!StringUtils.isBlank(textFieldTitle.getCharacters())){
            sendEmail.sendEmail(textFieldAddres.getText(),textFieldTitle.getText(),textAreaMessage.getText());
        }else {
            TrayNotificationController trayNotificationController = new TrayNotificationController().viewNotification("Warrning","You didn't enter the address or title");
        }


    }
    @FXML
    private void about(){
         openNewWindow.openWindow("aboutEmailSenderWindow.fxml","About EmailSender");

    }
    @FXML
    private void refresh(){
        checkingMessages.getListMessage().clear();
        run();
    }

    @Override
    public void run() {
        checkingMessages.checkMail(service.getHostPOP(),service.getMailStoreType(),user.getFROM(),user.getPASSWORD());
        labelCount.setText(String.valueOf(checkingMessages.getMessagesCout()));
       // listViewMessages.getItems().add(checkingMessages.getListStrings());
        listViewMessages.getItems().addAll(checkingMessages.getListMessage().keySet());

    }

    @FXML
    public void readMessage(){
        String a = (String) checkingMessages.getListMessage().get(listViewMessages.getSelectionModel().getSelectedItem());
        viewTextArea.setText(a);
    }
    @FXML
    public void preferences(){
        openNewWindow.openWindow("preferencesWindow.fxml","Preferences");
    }
    @FXML
    public void exitApp(){
        System.exit(0);
    }
}
