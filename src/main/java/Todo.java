public class Todo extends Task {
    protected String by;

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Todo create(String input) throws TodoException {
        String[] arr = input.split("\\s");
        if (arr.length == 1) {
            throw new TodoException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(input.substring(5));
    }

    @Override
    public Todo markAsDone() {
        return new Todo(this.description, true);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
