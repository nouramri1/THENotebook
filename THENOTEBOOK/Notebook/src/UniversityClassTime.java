import java.time.LocalTime;

public class UniversityClassTime {
    private String day;
    private LocalTime startTime;
    private LocalTime stopTime;

    public UniversityClassTime(String day, LocalTime startTime, LocalTime stopTime) {
        this.day = day;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public boolean timeBetween(LocalTime time) {
        return time.isAfter(startTime) && time.isBefore(stopTime);
    }

    @Override
    public String toString() {
        return day + " " + startTime + " - " + stopTime;
    }
}
