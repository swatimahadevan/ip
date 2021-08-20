import java.util.Scanner;
public class Duke {

    static String horizontalDividerLine = "____________________________________________________________";

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

    public static void echo(String userInput) {
        System.out.println(horizontalDividerLine);
        System.out.println(userInput);
        System.out.println(horizontalDividerLine);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        greet();
        String userInput = in.nextLine();
        while(!userInput.equals("bye")){
            echo(userInput);
            userInput = in.nextLine();
        }
        bye();
        in.close();
    }
}
