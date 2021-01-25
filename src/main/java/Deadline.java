public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toFileString() {
        return "D | " + ((isDone) ? 1 : 0) + " | " + description + " | " + by;
    }
}