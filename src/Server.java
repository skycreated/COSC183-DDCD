import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Server {

    boolean createCharacter = false;
    boolean updateCharacter = false;
    boolean deleteCharacter = false;
    boolean viewCharacter = false;

    boolean first = false;
    boolean level = false;
    boolean theClass = false;
    boolean race = false;

    int id = 0;

    String nl = System.lineSeparator();

    public void start(final int portNumber){

        try(var serverSocket = new ServerSocket(portNumber)){
            System.out.println("Server started. Waiting for clients on port " + portNumber);
            try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
                while(true) {
                    var clientSocket = serverSocket.accept();
                    executor.submit(() -> {
                        System.out.println("Client connected!");
                        var clientIP = clientSocket.getInetAddress().getHostAddress();
                        var clientPort = clientSocket.getPort();
                        try (var clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                             var writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                            String inputLine;
                            while ((inputLine = clientInput.readLine()) != null) {
                                System.out.println(clientIP + ": " + clientPort + " Received from client..." + inputLine);
                                //we want the menu options response here
                                Database db  = new Database();
                                if (inputLine.equalsIgnoreCase("A") && !(updateCharacter)) {
                                    createCharacter = true;
                                    viewCharacter = false;
                                    updateCharacter = false;
                                    deleteCharacter = false;

                                    //writer.println();

                                    CreateCharacter cc = new CreateCharacter(); //create character from character creator
                                    cc.generateCharacter(); //generate the character

                                    writer.println("Option A - Create New Random Character."+nl+"Generating Random Character..."+ nl + cc + nl +"Data Inserted Into Database: " + Arrays.toString(cc.characterArray()) + nl + "A = Create | B = View | C = Update | D = Delete"); //display the character Summary
                                    System.out.println(Arrays.toString(cc.characterArray())); //display the array in server
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

                                } else if (inputLine.equalsIgnoreCase("B") && !(updateCharacter)){
                                    viewCharacter = true;
                                    createCharacter = false;
                                    updateCharacter = false;
                                    deleteCharacter = false;
                                    writer.println("Option B - View Character.");

                                    writer.println("Enter the ID of the character you wish to view, or 'all' if you wish to view all character records: ");


                                } else if (inputLine.equalsIgnoreCase("C")) {
                                    updateCharacter = true;
                                    createCharacter = false;
                                    viewCharacter = false;
                                    deleteCharacter = false;

                                    first = true;
                                    writer.println("Option C - Update Character.");

                                    writer.println("Enter the ID of the character you would like to update: ");


                                } else if (inputLine.equalsIgnoreCase("D") && !(updateCharacter)) {
                                    deleteCharacter = true;
                                    createCharacter = false;
                                    viewCharacter = false;
                                    updateCharacter = false;
                                    writer.println("Option D - Delete Character.");

                                }else if (viewCharacter) { //if they chose to view character(s)
                                    if (inputLine.equalsIgnoreCase("all")) { //if they want to view all character records
                                        writer.println("Retrieving ALL records from database...." + nl + db.getAllData()); //get all records from database and print
                                        writer.println("A = Create | B = View | C = Update | D = Delete"); //menu options after records displayed so user can continue
                                    } else { //if they chose one record by ID number
                                        if (isInteger(inputLine.trim())) { //checks if the client input is a number or not
                                            writer.println(db.viewCharacter(Integer.parseInt(inputLine.trim()))); //if ID is a number, make sure it is integer and retrieve record from database for client to view
                                            System.out.println("Successfully completed client request to view record by ID.");
                                        }else{
                                            writer.println("That is not a valid ID number. Server is not able to retrieve any records.");
                                            System.out.println("Client wants to retrieve record but did not enter valid ID number.");
                                        }
                                        writer.println("A = Create | B = View | C = Update | D = Delete");
                                    }
                                    viewCharacter = false;

                                }else if (updateCharacter) { //if they want to update a character

                                   //FIRST CHECK IF THEY NEED TO ENTER ID
                                    if(first){ //checks to see if it is the first prompt (enter ID)
                                        if(isInteger(inputLine.trim())) { //checks to see if the input is an Integer
                                            id  = Integer.parseInt(inputLine.trim()); //if it is a number, make sure it is loaded as ID number
                                            String output = db.viewCharacter(id); //checks with the database to see what the outcome is
                                            if (output.equalsIgnoreCase("there are no records in the database. sorry.")) { //if there are no records with that ID number
                                                writer.println(db.viewCharacter(id)); //print the database response to client
                                                writer.println("Try entering a different ID: "); //ask client to try a different ID
                                                first = true; //this is confirmed that we are asking the client for the first prompt again (enter ID to update)
                                            }else{ //if the record is present
                                                writer.println(db.viewCharacter(id)); //print the record so the client can see
                                                writer.println("Please enter the FIELD that you would like to update: "); //prompt client to enter a field to change
                                                first = false; //no longer prompting for the first prompt (we do not want ID anymore as it is assigned now)
                                            }
                                        }else{ //if it is not a number entered when prompted for ID
                                            writer.println("That is not a valid ID number. It must be an Integer."+nl+"Please Try Again:"); //tell client to try entering ID again
                                            first = true; //this is confirmed that we are asking the client for the first prompt again (enter ID to update)
                                        }

                                   //THEN CHECK IF THEY NEED TO CHANGE LEVEL
                                    }else if (inputLine.equalsIgnoreCase("level") && !level) { //if the client wants to change the level (and level hasn't been prompted yet)
                                        level = true; //confirmed that they are requesting to change the level
                                        writer.println("What would you like to change the level to?");
                                        writer.println("Please note that the level must be between 1 and 20." + nl + "Enter LEVEL: "); //prompt for level between 1-20
                                    } else if (level){ //if they are changing the level field
                                        int number = 1;
                                        if (isInteger(inputLine.trim())) { //check that the level entered is a number
                                            try{
                                                number = Integer.parseInt(inputLine.trim()); //wrap as integer if the input is a number
                                            }catch (NumberFormatException e){ //handles number format issues (like if the user entered something else, and it tries to put into number format)
                                                e.printStackTrace();
                                            }
                                            if(number >=1 && number <= 20){ //if the level is between 1 and 20
                                                writer.println(db.updateLevel(id,  number) + nl + db.viewCharacter(id)); //update the level and show the change
                                                writer.println("A = Create | B = View | C = Update | D = Delete"); //menu options
                                                updateCharacter = false; //character has been updated so no longer active
                                                level = false; //confirms that we are no longer prompting for the level
                                            }else{ //if the level is not between 1 and 20
                                                writer.println("The level has to be between 1 and 20.");
                                                writer.println("Try entering a different level for character "+id+" please:");
                                                level = true; //confirms that we are still prompting for the level
                                            }
                                        }else { //if the input was NOT a number
                                            writer.println("That is not a valid Level...");
                                            writer.println("Please enter a valid Level: ");
                                        }

                                   //THEN CHECK IF THEY WANT TO CHANGE THE CLASS
                                    }else if(inputLine.equalsIgnoreCase("class") &&  !theClass) {
                                        theClass = true;
                                        writer.println("What would you like to change the class to?" + nl + "Class Options: Barbarian | Bard | Cleric | Druid | Fighter | Monk | Paladin | Rogue | Sorcerer | Warlock | Wizard | Ranger" + nl + "Please note that these are the recommended class options, but you can enter a homebrew class if you prefer.");
                                        writer.println("Enter CLASS: ");
                                    }else if (theClass){ //they are changing the class field
                                        theClass = false;
                                        updateCharacter = false;
                                        writer.println(db.updateClass(id, inputLine.trim()) + nl + db.viewCharacter(id));
                                        writer.println("A = Create | B = View | C = Update | D = Delete");

                                   //THEN CHECK IF THEY WANT TO CHANGE RACE
                                    }else if (inputLine.equalsIgnoreCase("race") && !race) {
                                        race = true;
                                        writer.println("What would you like to change the race to?" + nl + "Race Options: Human | Tiefling | High Elf | Wood Elf | Dark Elf | Half-Elf | Orc | Half-Orc | Halfling | Gnome | Dwarf | Dragonborn " + nl + "Please note that these are recommended race options, but you can enter a homebrew race if you prefer.");
                                        writer.println("Enter RACE: ");
                                    }else if (race){
                                        race = false;
                                        updateCharacter = false;
                                        writer.println(db.updateRace(id, inputLine.trim()) + nl + db.viewCharacter(id));
                                        writer.println("A = Create | B = View | C = Update | D = Delete");
                                    }


                            //IF THE INPUT IS UNEXPECTED (NOT A,B,C,D OR A DIFFERENT EXPECTED PROMPT)
                                } else{
                                    writer.println("The server does not understand this request");
                                    writer.println("A = Create | B = View | C = Update | D = Delete");
                                }

                            } //end of while loop that runs as long as input is NOT empty
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } //not catch here but that is OK
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    public static boolean isInteger(String str){
        try{
            Integer.parseInt(str.trim());
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }

}
