package controllers;

import helpers.TrayIcon;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 todo zrobić trayicon logo programu wyświetlane w rogu na pasku systemu,by zamknięcie okna nie zamykało programu
 todo uruchomienie metody closeApp faktycznie zamknie program poprzez kliknięcie w ikone z wysuwanymi opcjami
 */

public class TrayIconController implements Initializable, TrayIcon {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void closeApp() {

    }

    @Override
    public void openMainWindow() {

    }
}
