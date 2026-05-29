import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
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

            while(!(message = scanner.nextLine()).isEmpty()){ //while there is user input (message is not empty)

                writer.println(message);
                System.out.println("Response from Server: " + input.readLine()); //tells client the returned thing
                //we want this to be a confirmation of the client choice, then proceed with that operation
                Database db  = new Database();

                if (message.equalsIgnoreCase("A")){ //if they choose to create a random character

                    CreateCharacter cc = new CreateCharacter(); //create character from character creator
                    cc.generateCharacter(); //generate the character
                    System.out.println(cc); //display the character Summary
                    System.out.println(Arrays.toString(cc.characterArray())); //display the array

                    //CALL TO INSERT INTO DATABASE
                    db.insertCharacters (
                                    Integer.parseInt(cc.characterArray()[0]),
                                    cc.characterArray()[1],
                                    cc.characterArray()[2],
                                    cc.characterArray()[3],
                                    Integer.parseInt(cc.characterArray()[4]),
                                    cc.characterArray()[5],
                                    cc.characterArray()[6],
                                    Integer.parseInt(cc.characterArray()[7]),
                                    Integer.parseInt(cc.characterArray()[8]),
                                    Integer.parseInt(cc.characterArray()[9]),
                                    Integer.parseInt(cc.characterArray()[10]),
                                    Double.parseDouble(cc.characterArray()[11]),
                                    Double.parseDouble(cc.characterArray()[12]),
                                    Integer.parseInt(cc.characterArray()[13]),
                                    Integer.parseInt(cc.characterArray()[14]),
                                    Integer.parseInt(cc.characterArray()[15]),
                                    Integer.parseInt(cc.characterArray()[16]),
                                    Integer.parseInt(cc.characterArray()[17]),
                                    Integer.parseInt(cc.characterArray()[18])
                        );

                } else if (message.equalsIgnoreCase("B")){ //if they choose to view a character
                    System.out.println("Enter the ID of the character you wish to view, or 'all' if you wish to view all character records: ");
                    message = scanner.nextLine();
                    if (message.equalsIgnoreCase("all")){
                        db.getAllData("select * from characters");
                    } else {
                        db.viewCharacter(Integer.parseInt(message.trim()));
                    }


                }else if (message.equalsIgnoreCase("C")){ //if they choose to update an existing character
                    //db.updateCharacter();

                }else if (message.equalsIgnoreCase("D")){ //if they choose to delete a character
                    System.out.println("Enter the ID of the character you would like to DELETE: ");
                    message = scanner.nextLine();
                    db.deleteRecord(Integer.parseInt(message.trim()));
                    //System.out.println(message);

                }

            } //end of while loop

            socket.close();
            writer.close();
            input.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
