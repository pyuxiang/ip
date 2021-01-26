package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.Parser;

import java.time.LocalDateTime;

/**
 * Deadline class.
 *
 * A time-based class that tracks a description and an event time.
 *
 * @see duke.tasks.Deadline
 */
public class Event extends DateTask {

    /**
     * Constructor for an Event.
     *
     * @param description Description of Event.
     * @param dt LocalDateTime of Event.
     */
    public Event(String description, LocalDateTime dt) {
        this(description, dt, false);
    }
    /**
     * Constructor for an Event.
     *
     * @param description Description of Event.
     * @param dt LocalDateTime of Event.
     * @param isDone Whether task is completed.
     */
    public Event(String description, LocalDateTime dt, boolean isDone) {
        super(description, isDone);
        this.datetime = dt;
    }

    /**
     * Returns new Event by parsing user string input.
     *
     * Input validation for date present. Dates should be provided as an argument
     * to the '/at' flag, following the description, e.g.
     * {@code event <description> /at <datetime>}.
     * Datetime formats are specified in {@link duke.parser.Parser }.
     *
     * @param s User input.
     * @return Event.
     * @throws DukeExceptionIllegalArgument When description is empty, datetime is empty,
     *                                      or datetime is invalid.
     */
    public static Event parse(String s) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("The description of an event cannot be empty.");
        }

        String[] tokens = s.split(" /at ");
        if (tokens[0].equals("")) {
            throw new DukeExceptionIllegalArgument("The description of an event cannot be empty.");
        }
        if (tokens.length == 1 || tokens[1].equals("")) {
            throw new DukeExceptionIllegalArgument(
                    "An event must have both description and time,\ndelimited by '/at'.");
        }

        LocalDateTime dt = Parser.parseDate(tokens[1].strip());
        return new Event(tokens[0], dt);
    }

    /**
     * For pretty printing on stdout.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.formatDate(datetime) + ")";
    }

    /**
     * For minified printing into file.
     *
     * @return String representation of Event.
     */
    public String toFileString() {
        return "E\t" + ((isDone) ? 1 : 0) + "\t" + description + "\t" + Parser.formatDateISO(datetime);
    }
}
