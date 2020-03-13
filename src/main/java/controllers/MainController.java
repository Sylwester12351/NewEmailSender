package controllers;

import helpers.ShowLogo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mailControllers.CheckingMessages;
import mailControllers.SendEmail;
import properties.Service;
import properties.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * @Author: Sylwester Gawroński
 */
public class MainController implements Initializable {
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
        listViewMessages.setItems((ObservableList) checkingMessages.getListStrings());

    }

    @FXML
    private void sendMessage(){
        sendEmail.sendEmail(textFieldAddres.getText(),textFieldTitle.getText(),textAreaMessage.getText());
    }
    @FXML
    private void about(){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/aboutEmailSenderWindow.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setTitle("About EmailSender");
            stage.show();

    }
}
