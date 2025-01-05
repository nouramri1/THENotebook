import java.util.ArrayList;

public class Notebook {
    private ArrayList<Note> notes;
    private Note currentNote;
    private String filename;

    public Notebook() {
        this.notes = new ArrayList<>();
        this.currentNote = new Note(); // Always have a current note to work on
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public Note getCurrentNote() {
        return currentNote;
    }

    public void setCurrentNote(Note currentNote) {
        this.currentNote = currentNote;
    }

    public void clearNote() {
        if (currentNote != null && currentNote.getContent() != null) {
            notes.add(currentNote);
        }
        currentNote = new Note(); // Prepare a new note after clearing the old one
    }

    public ArrayList<Note> searchNotes(String query) {
        ArrayList<Note> results = new ArrayList<>();
        for (Note note : notes) {
            if (note.getContent().contains(query)) {
                results.add(note);
            }
        }
        return results;
    }
}
