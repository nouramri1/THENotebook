import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);

        // List to hold university classes
        ArrayList<UniversityClass> classes = new ArrayList<>();

        // Notebook object to manage notes
        Notebook notebook = new Notebook();
        notebook.clearNote();

        while (true) {
            System.out.println("Welcome to the Notebook App!");
            System.out.println("1. Add a Class");
            System.out.println("2. View Class Schedule");
            System.out.println("3. Add a Note");
            System.out.println("4. View Notes");
            System.out.println("5. Edit a Note");
            System.out.println("6. Delete a Note");
            System.out.println("7. Set Reminder for Class");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            // Adding input validation for numeric choice
            int choice = -1;
            try {
                choice = in.nextInt();
                in.nextLine(); // Clear the buffer after reading an integer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                in.nextLine(); // Clear the buffer in case of invalid input
                continue; // Prompt the user again
            }

            switch (choice) {
                case 1:
                    addClass(in, classes);
                    break;
                case 2:
                    viewClassSchedule(classes);
                    break;
                case 3:
                    addNoteToNotebook(in, notebook, classes);
                    break;
                case 4:
                    viewNotes(notebook);
                    break;
                case 5:
                    editNoteInNotebook(in, notebook);
                    break;
                case 6:
                    deleteNoteFromNotebook(in, notebook);
                    break;
                case 7:
                    setClassReminder(in, classes);
                    break;
                case 8:
                    System.out.println("Exiting Notebook App. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }

    private static void addClass(Scanner in, ArrayList<UniversityClass> classes) {
        System.out.println("Enter class name:");
        String name = in.nextLine();
        UniversityClass newClass = new UniversityClass();
        newClass.setName(name);

        System.out.println("Enter class day (e.g., Monday):");
        String day = in.nextLine();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm"); // Allows "1:00" or "01:00"

        // Validate class start time input
        LocalTime startTime = null;
        while (startTime == null) {
            System.out.println("Enter class start time (HH:mm):");
            String startTimeInput = in.nextLine();
            try {
                startTime = LocalTime.parse(startTimeInput, timeFormatter); // Use custom formatter
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the time in the format HH:mm.");
            }
        }

        // Validate class end time input
        LocalTime endTime = null;
        while (endTime == null) {
            System.out.println("Enter class end time (HH:mm):");
            String endTimeInput = in.nextLine();
            try {
                endTime = LocalTime.parse(endTimeInput, timeFormatter); // Use custom formatter
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the time in the format HH:mm.");
            }
        }

        newClass.addClassDayAndTime(day, startTime, endTime);
        classes.add(newClass);
        System.out.println("Class added successfully with start time: " + startTime + " and end time: " + endTime);
    }
    private static void viewClassSchedule(ArrayList<UniversityClass> classes) {
        System.out.println("Your class schedule:");
        for (UniversityClass universityClass : classes) {
            System.out.println(universityClass);
        }
    }

    private static void addNoteToNotebook(Scanner in, Notebook notebook, ArrayList<UniversityClass> classes) {
        System.out.println("Enter your note:");
        String noteText = in.nextLine();
        notebook.getCurrentNote().setContent(noteText);

        LocalDateTime time = LocalDateTime.now();
        notebook.getCurrentNote().setNoteTime(time);

        notebook.getCurrentNote().assignUniversityClass(classes);

        notebook.clearNote();
        System.out.println("Note added successfully.");
    }

    private static void viewNotes(Notebook notebook) {
        System.out.println("Here are your notes:");
        for (Note note : notebook.getNotes()) {
            System.out.println(note);
        }
    }

    private static void editNoteInNotebook(Scanner in, Notebook notebook) {
        System.out.println("Enter the index of the note you want to edit:");
        int index = in.nextInt();
        in.nextLine(); // Clear the buffer

        if (index < 0 || index >= notebook.getNotes().size()) {
            System.out.println("Invalid index. Please try again.");
            return;
        }

        Note noteToEdit = notebook.getNotes().get(index);
        System.out.println("Current content: " + noteToEdit.getContent());
        System.out.println("Enter the new content:");
        String newContent = in.nextLine();
        noteToEdit.setContent(newContent);
        System.out.println("Note edited successfully.");
    }

    private static void deleteNoteFromNotebook(Scanner in, Notebook notebook) {
        System.out.println("Enter the index of the note you want to delete:");
        int index = in.nextInt();
        in.nextLine(); // Clear the buffer

        if (index < 0 || index >= notebook.getNotes().size()) {
            System.out.println("Invalid index. Please try again.");
            return;
        }

        notebook.getNotes().remove(index);
        System.out.println("Note deleted successfully.");
    }

    private static void setClassReminder(Scanner in, ArrayList<UniversityClass> classes) {
        System.out.println("Select a class to set a reminder for:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println(i + ": " + classes.get(i).getName());
        }

        int index = in.nextInt();
        in.nextLine(); // Clear the buffer

        if (index < 0 || index >= classes.size()) {
            System.out.println("Invalid class. Please try again.");
            return;
        }

        UniversityClass selectedClass = classes.get(index);
        LocalTime classStartTime = selectedClass.getClassTimes().get(0).getStartTime();

        // Set a reminder for 10 minutes before the class starts
        LocalTime reminderTime = classStartTime.minusMinutes(10);
        System.out.println("Reminder set for " + reminderTime + " for class " + selectedClass.getName());
    }
}
