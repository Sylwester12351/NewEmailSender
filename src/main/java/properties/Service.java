package properties;

/**
 * @Author: Sylwester Gawroński
 */
public class Service {
    private static String Host = "smtp.gmail.com";
    private static String PORT = String.valueOf(465);

    private String hostPOP = "pop.gmail.com"; //pop.gmail.com
    private String mailStoreType = "pop3";

    public static String getHost() {
        return Host;
    }

    public static String getPORT() {
        return PORT;
    }

    public String getHostPOP() {
        return hostPOP;
    }

    public String getMailStoreType() {
        return mailStoreType;
    }
}
