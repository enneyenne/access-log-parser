import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;


public class Statistics {

    private static long totalTraffic = 0;
    private static int visitsCount = 0;
    private static int errorResponseCount = 0;
    private static LocalDateTime minTime;
    private static LocalDateTime maxTime;

    private static final HashSet<String> ipList = new HashSet<>();
    private static final HashMap<String, Integer> uniqueVisit = new HashMap<>();
    private static final HashMap<LocalDateTime, Integer> requestMap = new HashMap<>();
    private final HashSet<String> domainSet = new HashSet<>();


    public Statistics() {
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
    }

    public void addEntry(LogEntry logEntry) {
        totalTraffic += logEntry.getResponseSize();
        LocalDateTime currentDateTime = logEntry.getTime();
        if (currentDateTime.isBefore(minTime)) minTime = currentDateTime;
        if (currentDateTime.isAfter(maxTime)) maxTime = currentDateTime;
        if (logEntry.getUserAgent() != null  && !logEntry.getUserAgent().isBot()) {
            visitsCount++;
            if (!requestMap.containsKey(logEntry.getTime())) {
                requestMap.put(logEntry.getTime(), 1);
            } else {
                int t = requestMap.get(logEntry.getTime());
                requestMap.replace(logEntry.getTime(), ++t);
            }
            if (!uniqueVisit.containsKey(logEntry.getIpAddr())) uniqueVisit.put(logEntry.getIpAddr(), 1);
            else {
                int c = uniqueVisit.get(logEntry.getIpAddr());
                uniqueVisit.replace(logEntry.getIpAddr(), ++c);
            }
        }
        if ((logEntry.getResponseCode() > 399) && (logEntry.getResponseCode() < 600))
            errorResponseCount++;

        ipList.add(logEntry.getIpAddr());

        String domain = logEntry.getReferer();
        if (domain.split("//").length > 2) {
            if (!domainSet.contains(logEntry.getReferer().trim().split("//")[1].trim().split("/")[0]))
                domainSet.add(logEntry.getReferer().trim().split("//")[1].trim().split("/")[0].trim().replace("www.", ""));
        }
    }
    public static double getTrafficRate() {
        long time = ChronoUnit.SECONDS.between(minTime, maxTime);
        return Math.round(((double) totalTraffic / time) * 1000) / 1000D;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }

    public HashSet<String> getIpList() {
        return ipList;
    }

    public static int getAverageVisitsPerHour() {
        return visitsCount / maxTime.getHour() - minTime.getHour();
    }

    public static int getAverageErrorResponsePerHour() {
        return errorResponseCount / maxTime.getHour() - minTime.getHour();
    }

    public static int getAverageVisitsPerUser() {
        return visitsCount / ipList.size();
    }

    public static int getMaximumRequestsPerSecond() {
        return requestMap.values().stream().max(Integer::compareTo).get();
    }

    public static int getMaximumVisitsPerUser() {
        return uniqueVisit.values().stream().max(Integer::compareTo).get();
    }
}