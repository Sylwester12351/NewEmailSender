package helpers;

import javafx.scene.image.Image;

public abstract class ShowLogo {
    private static Image logo = new Image("icoEmailSender2.png");

    public static Image getLogo() {
        return logo;
    }
}
