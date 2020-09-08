/**
 * A subclass of Command class.
 * Handle "bye" command.
 */
public class ByeCommand extends Command {
    private static final String END = "Bye. Hope to see you again soon!";

    /**
     * Executes the command.
     * @param tasks an object includes a list of tasks.
     * @param ui ui.
     * @param storage the storage that working on data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return END;
    }

    /**
     * Returns isExit to show stop reading user input.
     * @return true to indicate an end.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
