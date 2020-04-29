package controllers;

import config.ReadConfig;
import config.SaveConfig;
import helpers.OpenNewWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import properties.User;
import controllers.CheckBoxesControl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: Sylwester Gawroński
 */
public class LoginController implements Initializable {
    private OpenNewWindow openNewWindow = new OpenNewWindow();
    private CheckBoxesControl checkBoxesControl = new CheckBoxesControl();
    private Parent root1;
    private SaveConfig saveConfig = new SaveConfig();
    private ReadConfig readConfig = new ReadConfig();
    private TrayNotificationController trayNotificationController = new TrayNotificationController();

    @FXML
    TextField emailTextField;
    @FXML
    PasswordField passTextField;
    @FXML
    Button loginButton;
    @FXML
    CheckBox checkBoxRemember;
    @FXML
    CheckBox checkBoxAutoLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (checkBoxesControl.getRemember()){
            checkBoxRemember.setSelected(true);

            readConfig.ReadUserConfig();
            emailTextField.setText(readConfig.getUserLogin());
            passTextField.setText(readConfig.getUserPass());
        }
        else {
            checkBoxRemember.setSelected(false);
        }
        if (checkBoxesControl.getAutoLogin()){
            checkBoxAutoLogin.setSelected(true);
            login();

        }else {
            checkBoxAutoLogin.setSelected(false);
        }
    }

    @FXML
    private void Clicklogin(ActionEvent actionEvent) {

        if (checkBoxAutoLogin.isSelected()){
            checkBoxesControl.setAutoLogin(true);
        }
        else {
            checkBoxesControl.setAutoLogin(false);
        }
        if (checkBoxRemember.isSelected()){
            checkBoxesControl.setRemember(true);
        }
        else {
            checkBoxesControl.setRemember(false);
        }
        if (StringUtils.isBlank(emailTextField.getCharacters()) || StringUtils.isBlank(passTextField.getCharacters())) {
            trayNotificationController.viewNotification("Error", "Bad Login or password");

        } else {
            login();
            Stage stagel = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stagel.close(); // Close login window
        }
    }

    private void login(){
        // Update Configuration checkBoxes
        saveConfig.saveCheckBoxes();
        System.out.println(checkBoxesControl.getAutoLogin());//TEST
        System.out.println(checkBoxesControl.getRemember());
        String email = emailTextField.getText();
        String pass = passTextField.getText();
        User user = new User();
        user.setFROM(email);
        user.setPASSWORD(pass);

        if (checkBoxRemember.isSelected()){
            saveConfig.saveUser();
        }
        openNewWindow.openWindow("mainWindow.fxml","EmailSender");
    }
}
