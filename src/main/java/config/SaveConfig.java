package config;

import controllers.CheckBoxesControl;
import properties.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveConfig {
    CheckBoxesControl checkBoxesControl = new CheckBoxesControl();

    public void saveUser(){
        if (checkBoxesControl.getRemember()){
            System.out.println("Zapisuje login");
            User user = new User();
            BufferedWriter bufferedWriter;
            {
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter("CONFIG-User"));
                    bufferedWriter.write("CONFIG_Start{User="+user.getFROM());
                    bufferedWriter.write("-US}{Pass="+user.getPASSWORD());
                    bufferedWriter.write("-PS}CONFIG_END");
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("ConfigUS error save"+e);
                }
            }
        }
    }
    public void saveCheckBoxes(){
        CheckBoxesControl checkBoxesControl = new CheckBoxesControl();
        BufferedWriter bufferedWriter;
        {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter("CONFIGcheckboxes"));
                bufferedWriter.write("CONFIG_Start{SaveLogin="+checkBoxesControl.getRemember());
                bufferedWriter.write("-SL}{AutoLogin="+checkBoxesControl.getAutoLogin());
                bufferedWriter.write("-AL}CONFIG_END");
                bufferedWriter.close();
                System.out.println("ConfigSave");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Config error save"+e);
            }
        }
    }
}
