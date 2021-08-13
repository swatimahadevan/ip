public class Duke {
    public static void greet(){
        //Function to simply greet the user and exit
        String HorizontalLine = "____________________________________________________________";
        System.out.println(HorizontalLine);
        System.out.println("Hello, I'm Duke\n" + "What can I do for you?");
        System.out.println(HorizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HorizontalLine);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }
}
