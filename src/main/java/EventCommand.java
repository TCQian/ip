import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A subclass of Command.
 * Handles "event" command.
 */
public class EventCommand extends Command {
    private static final String TAB = "  ";
    private static final String ADD_TASK_TITLE = TAB + " Got it. I've added this task:";
    private String[] input;

    /**
     * Constructor.
     * @param input user input.
     */
    public EventCommand(String[] input) {
        super();
        this.input = input;
    }

    /**
     * Executes the command.
     * @param tasks a list of tasks.
     * @param ui ui.
     * @param storage the storage working on data file.
     * @throws EventException to show incorrect user input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EventException {
        ArrayList<Task> store = tasks.getTaskList();
        int index = 0;
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("/at")) {
                index = i;
                break;
            }
        }
        if (input.length == 1 || index == 1) { // no description
            throw new EventException(" ☹ OOPS!!! The description of a event cannot be empty.");
        } else if (index == input.length - 1 || index == 0) { //no time
            throw new EventException(" ☹ OOPS!!! The time of a event cannot be empty.");
        }
        String description = "";
        String time = "";
        for (int i = 1; i < index; i++) {
            description = description + input[i] + " ";
        }
        for (int i = index + 1; i < input.length; i++) {
            time = time + input[i] + " ";
        }

        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hhmm");
            date = formatter.parse(time.trim());
        } catch (ParseException e) {
            throw new EventException(" ☹ OOPS!!! The time of a deadline must be in the format of dd/M/yyyy hhmm.");
        }

        Event newTask = new Event(description.trim(), new SimpleDateFormat("MMM dd yyyy HH:mm").format(date));
        store.add(newTask);
        storage.save(new TaskList(store));
        return ADD_TASK_TITLE + "\n"
                + TAB + "   " + newTask + "\n"
                + TAB + " Now you have " + store.size() + " tasks in the list.";
    }

    /**
     * Returns isDone to stop user from entering command.
     * @return false to continue to accept user input.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
