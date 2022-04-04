package src;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("19376336-王孙悦");
        Scanner in = new Scanner(System.in);
        String argStr = in.nextLine();
        while (!argStr.equals("QUIT")) {
            System.out.println("Hello, World!");
            argStr = in.nextLine();
        }
        System.out.println("----- Good Bye! -----");
        System.exit(0);
    }
}
