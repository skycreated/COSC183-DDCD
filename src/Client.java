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
            var writer = new PrintWriter(socket.getOutputStream(), true);
            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while(!(message = scanner.nextLine()).isEmpty()){
                writer.println(message);
                System.out.println("Response from Server: " + input.readLine());
            }

            socket.close();
            writer.close();
            input.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
