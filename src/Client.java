import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
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

            while(!(message = scanner.nextLine()).isEmpty()){
                writer.println(message);
                System.out.println("Response from Server: " + input.readLine()); //tells client the returned thing
                //we want this to be a confirmation of the client choice, then proceed with that operation


                Database db  = new Database();

                if (message.equalsIgnoreCase("A")){
                    //System.out.println("Create");
                    CreateCharacter cc = new CreateCharacter();
                    cc.generateCharacter();
                    System.out.println(cc);
                    System.out.println(Arrays.toString(cc.characterArray()));
                    String[] insertCharacter = {
                            cc.characterArray()[0],
                            cc.characterArray()[1],
                            cc.characterArray()[2],
                            cc.characterArray()[3],
                            cc.characterArray()[4],
                            cc.characterArray()[5],
                            cc.characterArray()[6],
                            cc.characterArray()[7],
                            cc.characterArray()[8],
                            cc.characterArray()[9],
                            cc.characterArray()[10],
                            cc.characterArray()[11],
                            cc.characterArray()[12],
                            cc.characterArray()[13],
                            cc.characterArray()[14],
                            cc.characterArray()[15],
                            cc.characterArray()[16],
                            cc.characterArray()[17],
                            cc.characterArray()[18],
                    };

                    db.insertPrepStatement (
                                    Integer.parseInt(insertCharacter[0]),
                                    insertCharacter[1],
                                    insertCharacter[2],
                                    insertCharacter[3],
                                    Integer.parseInt(insertCharacter[4]),
                                    insertCharacter[5],
                                    insertCharacter[6],
                                    Integer.parseInt(insertCharacter[7]),
                                    Integer.parseInt(insertCharacter[8]),
                                    Integer.parseInt(insertCharacter[9]),
                                    Integer.parseInt(insertCharacter[10]),
                                    Double.parseDouble(insertCharacter[11]),
                                    Double.parseDouble(insertCharacter[12]),
                                    Integer.parseInt(insertCharacter[13]),
                                    Integer.parseInt(insertCharacter[14]),
                                    Integer.parseInt(insertCharacter[15]),
                                    Integer.parseInt(insertCharacter[16]),
                                    Integer.parseInt(insertCharacter[17]),
                                    Integer.parseInt(insertCharacter[18])
                        );

                } else if (message.equalsIgnoreCase("B")){

                }else if (message.equalsIgnoreCase("C")){

                }else if (message.equalsIgnoreCase("D")){

                }



            }

            socket.close();
            writer.close();
            input.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
