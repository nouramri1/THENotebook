import java.time.LocalTime;
import java.util.ArrayList;

public class UniversityClass {
    private String name;
    private ArrayList<UniversityClassTime> classTimes;

    public UniversityClass() {
        this.classTimes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UniversityClassTime> getClassTimes() {
        return classTimes;
    }

    public void addClassDayAndTime(String day, LocalTime startTime, LocalTime stopTime) {
        UniversityClassTime classTime = new UniversityClassTime(day, startTime, stopTime);
        classTimes.add(classTime);
    }

    public boolean hasClassAt(LocalTime time) {
        for (UniversityClassTime classTime : classTimes) {
            if (classTime.timeBetween(time)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "UniversityClass{" +
                "name='" + name + '\'' +
                ", classTimes=" + classTimes +
                '}';
    }
}
