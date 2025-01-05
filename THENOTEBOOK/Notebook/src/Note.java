import java.time.LocalDateTime;
import java.util.ArrayList;

public class Note {
    private String content;
    private LocalDateTime noteTime;
    private UniversityClass assignedClass;
    private ArrayList<Link> links; // List of links associated with the note
    private LocalDateTime reminderTime; // Reminder time for the note

    public Note() {
        this.links = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(LocalDateTime noteTime) {
        this.noteTime = noteTime;
    }

    public UniversityClass getAssignedClass() {
        return assignedClass;
    }

    public void setAssignedClass(UniversityClass assignedClass) {
        this.assignedClass = assignedClass;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void addLink(Link link) {
        this.links.add(link);
    }

    public void removeLink(Link link) {
        this.links.remove(link);
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public void assignUniversityClass(ArrayList<UniversityClass> classes) {
        for (UniversityClass cls : classes) {
            if (cls.hasClassAt(noteTime.toLocalTime())) {
                assignedClass = cls;
                break;
            }
        }
    }

   @Override
public String toString() {
    return "Note{" +
            "content='" + content + '\'' +  // Correct single quote escaping
            ", noteTime=" + noteTime +
            ", assignedClass=" + assignedClass +
            ", links=" + links +
            ", reminderTime=" + reminderTime +
            '}';
}
}

