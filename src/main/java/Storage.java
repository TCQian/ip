import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(new File(filePath));
            ArrayList<Task> store = new ArrayList<>(100);
            
            while (sc.hasNextLine()) {
                String[] str = sc.nextLine().split("\\|");
                String title = str[0].trim();
                boolean isDone = str[1].trim().equals(1 + "");
                if (title.equals("T")) {
                    store.add(new Todo(str[2].trim(), isDone));
                } else if (title.equals("D")) {
                    store.add(new Deadline(str[2].trim(), isDone, str[3].trim()));
                } else {
                    store.add(new Event(str[2].trim(), isDone, str[3].trim()));
                }
            }
            return store;
            
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
    }
    
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for(Task task : tasks.getTaskList()) {
                fw.write(task.stringify() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
