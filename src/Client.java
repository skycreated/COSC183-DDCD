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
            System.out.println("Hello client! Select A for Create New Random Character | Select B for View Character | Select C for Update Character | Select D for Delete Character");
            var writer = new PrintWriter(socket.getOutputStream(), true);
            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while(!(message = scanner.nextLine()).isEmpty()){
                writer.println(message);
                System.out.println("Response from Server: " + input.readLine()); //tells client the returned thing
                //we want this to be a confirmation of the client choice, then proceed with that operation

            }

            socket.close();
            writer.close();
            input.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
