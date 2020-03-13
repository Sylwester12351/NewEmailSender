package config;

import controllers.CheckBoxesControl;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadConfig {
    CheckBoxesControl checkBoxesControl = new CheckBoxesControl();
    private String SL,AL,UserLogin,UserPass,textUser,text;

    public ReadConfig ReadUserConfig(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("CONFIG-User"));
            textUser = bufferedReader.readLine();

            if (!StringUtils.isBlank(textUser)) {
                int startIndex = textUser.indexOf("CONFIG_Start{User=") + 18;
                int endIndex = textUser.indexOf("-US}");
                UserLogin = textUser.substring(startIndex, endIndex);
            }
            if (!StringUtils.isBlank(textUser)) {
                int startIndex = textUser.indexOf("{Pass=") + 6;
                int endIndex = textUser.indexOf("-PS}CONFIG_END");
                UserPass = textUser.substring(startIndex, endIndex);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public ReadConfig ReadCheckBoxesConfig() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("CONFIGcheckboxes"));
            text = bufferedReader.readLine();
            System.out.println(text);
            if (!StringUtils.isBlank(text)) {
                int startIndex = text.indexOf("CONFIG_Start{SaveLogin=") + 23;
                int endIndex = text.indexOf("-SL}");
                SL = text.substring(startIndex, endIndex);
                if (SL.contains("true")){
                    checkBoxesControl.setRemember(true);
                }
                else {
                    checkBoxesControl.setRemember(false);
                }
                System.out.println(SL);
            }
            if (!StringUtils.isBlank(text)) {
                int startIndex = text.indexOf("{AutoLogin=") + 11;
                int endIndex = text.indexOf("-AL}CONFIG_END");
                AL = text.substring(startIndex, endIndex);
                if (AL.contains("true")){
                    checkBoxesControl.setAutoLogin(true);
                }else {
                    checkBoxesControl.setAutoLogin(false);
                }
                System.out.println(AL);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public String getUserLogin() {
        return UserLogin;
    }

    public String getUserPass() {
        return UserPass;
    }
}

