import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {

    private final String ipAddr;
    private final LocalDateTime time;
    private final HttpMethods method;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String[] lineFragments) {
        this.ipAddr = lineFragments[0];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy:HH:mm:s", Locale.ENGLISH);
        this.time = LocalDateTime.parse(lineFragments[3].replaceAll("\\/", "-")
                .replaceAll("\\[", ""), dateTimeFormatter);
        this.method = HttpMethods.valueOf(lineFragments[5].replaceAll("\"",""));
        this.path = lineFragments[6];
        this.responseCode = Integer.parseInt(lineFragments[8]);
        this.responseSize = Integer.parseInt(lineFragments[9]);
        this.referer = lineFragments[10];
        this.userAgent = new UserAgent(lineFragments[11]);
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethods getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddr='" + ipAddr + '\'' +
                ", time=" + time +
                ", method=" + method +
                ", path='" + path + '\'' +
                ", responseCode=" + responseCode +
                ", responseSize=" + responseSize +
                ", referer='" + referer + '\'' +
                ", userAgent=" + userAgent +
                '}';
    }
}


