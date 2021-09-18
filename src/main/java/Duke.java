import command.Command;
import exceptions.DukeInvalidArgumentException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.UI;

import java.io.FileNotFoundException;

public class Duke {
    private static final String FILEPATH = "data/saveData.txt";
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath){
        storage = new Storage();
        try{
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e){
            UI.printFileNotFoundMessage();
            tasks = new TaskList();
        }
    }

    public void run(){
        UI.printGreetMessage();
        boolean isExit = false;
        while(!isExit){
            try {
                String input = UI.getUserInput();
                Command c = Parser.parse(tasks, input);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeInvalidArgumentException e) {
                UI.printInvalidInputMessage();
            }
        }
    }
    public static void main(String[] args) {
        new Duke(FILEPATH).run();
    }
}