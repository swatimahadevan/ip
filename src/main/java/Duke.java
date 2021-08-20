import java.util.Scanner;
public class Duke {

    static String horizontalDividerLine = "____________________________________________________________";
    private static String[] tasksList = new String[100];
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
        tasksList[numberOfTasks++] = task;
        System.out.println(horizontalDividerLine);
        System.out.println("Added: " + task);
        System.out.println(horizontalDividerLine);
    }

    public static void printTasksList(){
        System.out.println(horizontalDividerLine);
        for(int i = 0; i < numberOfTasks; i ++){
            System.out.println(i+1 + ". " + tasksList[i]);
        }
        System.out.println(horizontalDividerLine);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        greet();
        String input = in.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")) {
                printTasksList();
            } else {
                addTaskToList(input);
            }
            input = in.nextLine();
        }
        bye();
        in.close();
    }
}
