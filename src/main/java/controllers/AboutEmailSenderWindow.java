package controllers;

import helpers.ShowLogo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class AboutEmailSenderWindow implements Initializable {
    @FXML
    ImageView imageView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(ShowLogo.getLogo());
    }
}
