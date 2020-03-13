package controllers;

public class CheckBoxesControl {
    private static Boolean remember = false;
    private static Boolean autoLogin = false;

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        CheckBoxesControl.remember = remember;
    }

    public Boolean getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(Boolean autoLogin) {
        CheckBoxesControl.autoLogin = autoLogin;
    }

    @Override
    public String toString() {
        return "controllers.CheckBoxesControl{" +
                "remember=" + remember +
                ", autoLogin=" + autoLogin +
                '}';
    }
}

