package controllers;

import com.github.plushaze.traynotification.animations.Animations;
import com.github.plushaze.traynotification.notification.TrayNotification;
import helpers.ShowLogo;
import javafx.scene.image.Image;
import javafx.util.Duration;
/**
 * @Author: Sylwester Gawtoński
 */
public class TrayNotificationController extends ShowLogo {
    public TrayNotificationController viewNotification(String title, String message){
        TrayNotification tray = new TrayNotification();
        tray.setAnimation(Animations.POPUP);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setImage(ShowLogo.getLogo());
        tray.showAndDismiss(Duration.seconds(3));
        return null;
    }
}
