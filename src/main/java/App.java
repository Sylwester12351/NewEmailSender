import config.ReadConfig;
import controllers.CheckBoxesControl;
import controllers.TrayNotificationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Author: Sylwester Gawroński
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
       // CheckBoxesControl checkBoxesControl = new CheckBoxesControl();
        ReadConfig readConfig = new ReadConfig().ReadCheckBoxesConfig();
        Parent root = FXMLLoader.load(getClass().getResource("/loginWindow.fxml"));
        primaryStage.setTitle("NewEmailSender Login");
        primaryStage.setScene(new Scene(root, 400, 375));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
        TrayNotificationController TrayNotificationController = new TrayNotificationController()
                .viewNotification("Welcome",":)");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
