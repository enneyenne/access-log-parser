import java.time.LocalDateTime;


public class Statistics {

    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        long totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;
    }

    public void addEntry(LogEntry logEntry) {
        if (minTime.compareTo(logEntry.getTime()) > 0)
            {minTime = logEntry.getTime();}
        if (maxTime.compareTo(logEntry.getTime()) < 0)
            {maxTime = logEntry.getTime();}
    }
    public double getTrafficRate (long totalTraffic) {
        return totalTraffic / (maxTime.getHour() - minTime.getHour());
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }
}