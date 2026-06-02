import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {


    //START CLIENT
    public void start(final int portNumber, final Scanner scanner){
        try{
            var socket = new Socket("localhost",portNumber); //requires port number and IP address
            //we use local host to simulate communication on same system (server on same system as client)
            //in other cases where server is on different system we would need that IP address but for this we use localhost

            System.out.println("Socket created, ready to send data to server.");
            System.out.println("Hello client! \nSelect A for Create New Random Character | Select B for View Character | Select C for Update Character | Select D for Delete Character");
            var writer = new PrintWriter(socket.getOutputStream(), true);
            //we want auto flush to be true so that when we call print line it ensures that the data is immediately visible
            //with network protocols you will need the other side to see the output immediately
            //if you do not use autoflush, the other side might wait indefinitely due to data being stuck in memory
            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while(!(message = scanner.nextLine()).isEmpty()){ //while there is user input/data present (while it is not empty)


                if (message.equalsIgnoreCase("EXIT")){
                    System.exit(0);
                }else{

                    writer.println(message); //sends user input to server
                    System.out.println("Response from Server: " + input.readLine()); //tells client the returned choice (create, view, update, delete) or the first response

                    do{
                        System.out.println(input.readLine()); //reads all the lines that need to be conveyed to client from server based on client choice or the second response
                    }while(input.ready());


                }

                //we have two cases of the input.readline because each section of the server menu has 2 writes

            } //end of while loop


            //close everything:
            socket.close();
            writer.close();
            input.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

} //end of client class
