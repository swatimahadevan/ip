import java.util.Scanner;
public class Duke {

    static String horizontalDividerLine = "____________________________________________________________";
    private static String[] tasksList = new String[100];
    private static Boolean[] isTaskDone = new Boolean[100];
    private static int numberOfTasks = 0;

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
        tasksList[numberOfTasks] = task;
        isTaskDone[numberOfTasks++] = false;
        System.out.println(horizontalDividerLine);
        System.out.println("added: " + task);
        System.out.println(horizontalDividerLine);
    }

    public static void printTasksList(){
        System.out.println(horizontalDividerLine);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < numberOfTasks; i ++){
            System.out.println(i+1 + ". " + (isTaskDone[i] ? "[X] " : "[ ] ") + tasksList[i]);
        }
        System.out.println(horizontalDividerLine);
    }

    public static void markTaskAsDone(int taskIndex){
        isTaskDone[taskIndex- 1] = true;
        System.out.println(horizontalDividerLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasksList[taskIndex- 1]);
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
