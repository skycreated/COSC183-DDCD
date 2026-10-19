//2026
//Skylar Magouliotis
//Spencer Mulder
//Kyle Yee


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final int PORT_NUMBER = 1234;

        try (var scanner = new Scanner(System.in)) {
            System.out.println("Is this a Server? (y/n)"); //Check if the server is running first
            //then if it is the server:
            if (scanner.nextLine().equalsIgnoreCase("y")) { //equals ignore case ignores if a capital Y was entered
                //new instance of the server object and run it
                new Server().start(PORT_NUMBER);
            } else { //if it is NOT a server
                //new instance of the client object and run it
                new Client().start(PORT_NUMBER, scanner);
            }
        }

    }

}