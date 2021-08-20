import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    static String horizontalDividerLine = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalDividerLine);
        System.out.println("Hello, I'm Duke\n" + "What can I do for you?");
        System.out.println(horizontalDividerLine);
    }

    public static void bye() {
        System.out.println(horizontalDividerLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDividerLine);
    }

    public static void addTaskToList(String task){
        tasks.add(new Task(task));
        System.out.println(horizontalDividerLine);
        System.out.println("added: " + task);
        System.out.println(horizontalDividerLine);
    }

    public static void printTasksList(){
        System.out.println(horizontalDividerLine);
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for(Task item: tasks){
            System.out.println((i++) + ". " + item.getStatusIcon() + " " + item.getDescription());
        }
        System.out.println(horizontalDividerLine);
    }

    public static void markTaskAsDone(int taskIndex){
        tasks.get(taskIndex - 1).markAsDone();
        System.out.println(horizontalDividerLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks.get(taskIndex - 1).getDescription());
        System.out.println(horizontalDividerLine);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        greet();
        String input = in.nextLine();
        while(!input.equals("bye")){
            String[] words = input.split(" ");
            if(input.equals("list")) {
                printTasksList();
            }
            else if(words[0].equals("done")) {
                int taskIndex = Integer.parseInt(words[1]);
                markTaskAsDone(taskIndex);
            }
            else {
                addTaskToList(input);
            }
            input = in.nextLine();
        }
        bye();
        in.close();
    }
}
