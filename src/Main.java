import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final int PORT_NUMBER = 1234;

        try (var scanner = new Scanner(System.in)) {
            System.out.println("Is this a Server? (y/n)");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                new Server().start(PORT_NUMBER);
            } else {
                new Client().start(PORT_NUMBER, scanner);
            }
        }


        Database db = new Database();

        //SELECT FROM DATABASE
        //we need a way to have the client select the ID number to display the record associated with that ID


    }
}