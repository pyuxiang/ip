package duke.commands;

import duke.exceptions.*;
import duke.storage.FileLoader;
import duke.tasks.*;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class DukeCommand {

    public boolean isExit() {
        return false;
    }

    public static DukeCommand parse(String line)
            throws DukeExceptionCommandNotFound, DukeExceptionIllegalArgument {
        line = line.strip(); // input sanitization
        String arg = "", cmd = line;
        if (line.contains(" ")) {
            int splitIdx = line.indexOf(' ');
            cmd = line.substring(0, splitIdx); // automatically stripped
            arg = line.substring(splitIdx+1).strip();
        }
        cmd = cmd.toLowerCase();

        Task task;
        switch (cmd) {
        case "bye":
            return new DukeCommandBye();
        case "list":
            return new DukeCommandList();
        case "done":
            return new DukeCommandDone(arg);
        case "delete":
            return new DukeCommandDelete(arg);
        case "event":
            task = Event.parse(arg);
            return new DukeCommandAdd(task);
        case "todo":
            task = Todo.parse(arg);
            return new DukeCommandAdd(task);
        case "deadline":
            task = Deadline.parse(arg);
            return new DukeCommandAdd(task);
        default:
            throw new DukeExceptionCommandNotFound("Command '" + cmd + "' is invalid. Valid commands:"
                    + "\nbye, list, done, delete, event, todo, deadline");
        }
    }

    public abstract void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeException;
}
