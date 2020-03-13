package properties;
/**
 * @Author: Sylwester Gawroński
 */
public class User {
    private static String FROM = "";
    private static String PASSWORD = "";

    public String getFROM() {
        return FROM;
    }

    public void setFROM(String FROM) {
        User.FROM = FROM;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        User.PASSWORD = PASSWORD;
    }

    @Override
    public String toString() {
        return "User{" +
                "FROM='" + FROM + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                '}';
    }
}
