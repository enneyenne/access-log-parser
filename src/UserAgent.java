public class UserAgent {

    private final String operatingSystem;
    private final String browser;

    public UserAgent(String userAgent) {

        String operatingSystem="";
        String browser="";

        if (userAgent.contains("Windows")) {operatingSystem="Windows";}
        if (userAgent.contains("Linux")) {operatingSystem="Linux";}
        if (userAgent.contains("Macintosh") || userAgent.contains("Mac")) {operatingSystem="Macintosh";}

        if (userAgent.contains("Firefox")) {browser="Firefox";}
        if (userAgent.contains("Chrome")) {browser="Chrome";}
        if (userAgent.contains("Opera") || userAgent.contains("OPR") || userAgent.contains("Presto")) {browser="Opera";}
        if (userAgent.contains("Edg")) {browser="Edge";}
        if (userAgent.contains("Safari")) {browser="Safari";}
        if (userAgent.contains("Safari")) {browser="Safari";}

        this.operatingSystem = operatingSystem;
        this.browser = browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "operatingSystem='" + operatingSystem + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}