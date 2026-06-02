import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void start(final int portNumber, final Scanner scanner){
        try{
            var socket = new Socket("localhost",portNumber);
            System.out.println("Socket created, ready to send data to server.");
            System.out.println("Hello client! \nSelect A for Create New Random Character | Select B for View Character | Select C for Update Character | Select D for Delete Character");
            var writer = new PrintWriter(socket.getOutputStream(), true);
            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            //Database db  = new Database();
            while(!(message = scanner.nextLine()).isEmpty()){ //while there is user input (message is not empty)

                writer.println(message); //sends user input to server
                System.out.println("Response from Server: " + input.readLine()); //tells client the returned choice (create, view, update, delete) or the first response

                do{
                    System.out.println(input.readLine()); //reads all the lines that need to be conveyed to client from server based on client choice or the second response
                }while(input.ready());


            } //end of while loop


            socket.close();
            writer.close();
            input.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }





}
