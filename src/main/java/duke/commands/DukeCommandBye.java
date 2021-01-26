package duke.commands;

import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Exit command.
 *
 * Allows main loop to terminate program.
 */
public class DukeCommandBye extends DukeCommand {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }
}
