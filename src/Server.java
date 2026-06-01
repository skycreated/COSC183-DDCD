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
    boolean name = false;
    boolean alignment = false;
    boolean background = false;
    boolean AC = false;
    boolean HP = false;
    boolean speed = false;
    boolean weight = false;
    boolean height = false;
    boolean age = false;
    boolean strength = false;
    boolean con = false;
    boolean dex = false;
    boolean wis = false;
    boolean theInt = false;
    boolean cha = false;

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

                                    writer.println("Option A - CREATE New Random Character."+nl+"Generating Random Character..."+ nl + cc + nl +"Data Inserted Into Database: " + Arrays.toString(cc.characterArray()) + nl + "A = Create | B = View | C = Update | D = Delete"); //display the character Summary
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
                                    writer.println("Option B - VIEW Character.");

                                    writer.println("Enter the ID of the character you wish to view, or 'all' if you wish to view all character records: ");


                                } else if (inputLine.equalsIgnoreCase("C") && !updateCharacter) {
                                    updateCharacter = true;
                                    createCharacter = false;
                                    viewCharacter = false;
                                    deleteCharacter = false;

                                    first = true;
                                    writer.println("Option C - UPDATE Character.");

                                    writer.println("Enter the ID of the character you would like to UPDATE: ");


                                } else if (inputLine.equalsIgnoreCase("D") && !(updateCharacter)) {
                                    deleteCharacter = true;
                                    createCharacter = false;
                                    viewCharacter = false;
                                    updateCharacter = false;
                                    writer.println("Option D - DELETE Character.");
                                    writer.println("Enter the ID of the character you would like to DELETE: ");

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

                                   //THEN CHECK IF THEY WANT TO CHANGE NAME
                                    }else if  (inputLine.equalsIgnoreCase("name") && !name) {
                                        name = true;
                                        writer.println("What would you like to name character " + id + "?");
                                        writer.println("Enter NAME: ");
                                    }else if (name) {
                                        name = false;
                                        updateCharacter = false;
                                        writer.println(db.updateName(id, inputLine.trim()) + nl + db.viewCharacter(id));
                                        writer.println("A = Create | B = View | C = Update | D = Delete");


                                   //THEN CHECK IF THEY WANT TO CHANGE ALIGNMENT
                                    }else if (inputLine.equalsIgnoreCase("alignment") && !alignment) {
                                        alignment = true;
                                        writer.println("What would you like to change the alignment to?" + nl + "Alignment Options: True Neutral | Lawful Good | Neutral Good | Chaotic Good | Lawful Neutral | Chaotic Neutral | Lawful Evil | Neutral Evil | Chaotic Evil");
                                        writer.println("Enter ALIGNMENT: ");
                                    }else if (alignment) {
                                        if (inputLine.equalsIgnoreCase("True Neutral") || inputLine.equalsIgnoreCase("Lawful Good") || inputLine.equalsIgnoreCase("Neutral Good") || inputLine.equalsIgnoreCase("Chaotic Good") || inputLine.equalsIgnoreCase("Lawful Evil") || inputLine.equalsIgnoreCase("Chaotic Evil") || inputLine.equalsIgnoreCase("Neutral Evil") || inputLine.equalsIgnoreCase("Chaotic Neutral") || inputLine.equalsIgnoreCase("Lawful Neutral")) {
                                            alignment = false;
                                            updateCharacter = false;
                                            writer.println(db.updateAlignment(id, inputLine.trim()) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else {
                                            writer.println("That is not a valid alignment...");
                                            writer.println("Try Again. Enter ALIGNMENT for character " + id + ": ");
                                        }



                                   //THEN CHECK IF THEY WANT TO CHANGE BACKGROUND
                                    }else if (inputLine.equalsIgnoreCase("Background") && !background) {
                                        background = true;
                                        writer.println("What would you like to change the background to?" + nl + "Background Options: Hermit | Charlatan | Hermit | Sage | Noble | Outlander | Soldier | Entertainer | Acolyte");
                                        writer.println("Enter BACKGROUND: ");
                                    }else if (background) {
                                        if (inputLine.equalsIgnoreCase("Hermit") || inputLine.equalsIgnoreCase("Sage") || inputLine.equalsIgnoreCase("Noble") || inputLine.equalsIgnoreCase("Outlander") || inputLine.equalsIgnoreCase("Soldier") || inputLine.equalsIgnoreCase("Acolyte") || inputLine.equalsIgnoreCase("Charlatan") || inputLine.equalsIgnoreCase("Entertainer")) {
                                            background = false;
                                            updateCharacter = false;
                                            writer.println(db.updateBackground(id, inputLine.trim()) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else {
                                            writer.println("That is not a valid background...");
                                            writer.println("Try Again. Enter BACKGROUND for character " + id + ": ");
                                        }

                                   //THEN CHECK IF THEY WANT TO CHANGE AC
                                    } else if ((inputLine.equalsIgnoreCase("AC") || inputLine.equalsIgnoreCase("armor class")) && !AC) {
                                        AC = true;
                                        writer.println("What would you like change the AC to? Please note Armor Class has to be a number value.");
                                        writer.println("Enter AC: ");
                                    }else if (AC) {
                                        if(isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            AC = false;
                                            updateCharacter = false;
                                            writer.println(db.updateAC(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid AC");
                                            writer.println("Try Again. Enter AC: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE HP
                                    }else if ((inputLine.equalsIgnoreCase("HP") || inputLine.equalsIgnoreCase("Hit Points")) && !HP){
                                        HP = true;
                                        writer.println("What would you like to change the HP to? Please note HP has to be a number value.");
                                        writer.println("Enter HP: ");
                                    }else if (HP){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            HP = false;
                                            updateCharacter = false;
                                            writer.println(db.updateHP(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else {
                                            writer.println("That is not a valid HP.");
                                            writer.println("Try Again. Enter HP: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE SPEED
                                    }else if (inputLine.equalsIgnoreCase("Speed") && !speed){
                                        speed = true;
                                        writer.println("What would you like to change the speed to? Please note character speed has to be a number value (feet).");
                                        writer.println("Enter SPEED: ");
                                    }else if  (speed){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try {
                                                number = Integer.parseInt(inputLine.trim());
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }
                                            HP = false;
                                            updateCharacter = false;
                                            writer.println(db.updateSpeed(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid Speed.");
                                            writer.println("Try Again. Enter SPEED: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE WEIGHT
                                    }else if (inputLine.equalsIgnoreCase("Weight") && !weight){
                                        weight = true;
                                        writer.println("What would you like to change the weight to? Please note character weight has to be a decimal value (pounds).");
                                        writer.println("Enter WEIGHT: ");
                                    }else if (weight){
                                        if (isDouble(inputLine.trim())) {
                                            double dnumber = 0.0;
                                            try{
                                                dnumber = Double.parseDouble(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            weight = false;
                                            updateCharacter = false;
                                            writer.println(db.updateWeight(id, dnumber) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid weight.");
                                            writer.println("Try Again. Enter WEIGHT: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE HEIGHT
                                    }else if (inputLine.equalsIgnoreCase("Height") &&  !height){
                                        height = true;
                                        writer.println("What would you like to change the height to? Please note character height has to be a decimal value (feet).");
                                        writer.println("Enter HEIGHT: ");
                                    } else if (height){
                                        if (isDouble(inputLine.trim())) {
                                            double dnumber = 0.0;
                                            try{
                                                dnumber = Double.parseDouble(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            height = false;
                                            updateCharacter = false;
                                            writer.println(db.updateHeight(id, dnumber) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid height.");
                                            writer.println("Try Again. Enter HEIGHT: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE THE AGE
                                    }else if (inputLine.equalsIgnoreCase("Age") && !age){
                                        age = true;
                                        writer.println("What would you like to change the age to? Please note character age has to be a number value (years).");
                                        writer.println("Enter AGE: ");
                                    }else if (age){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            age = false;
                                            updateCharacter = false;
                                            writer.println(db.updateAge(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid age.");
                                            writer.println("Try Again. Enter AGE: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE THE STRENGTH
                                    }else if ((inputLine.equalsIgnoreCase("Strength") || inputLine.equalsIgnoreCase("STR") ) && !strength){
                                        strength = true;
                                        writer.println("What would you like to change the strength score to? Please note that character strength has to be number.");
                                        writer.println("Enter STRENGTH: ");
                                    }else if (strength){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            strength = false;
                                            updateCharacter = false;
                                            writer.println(db.updateStrength(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid strength.");
                                            writer.println("Try Again. Enter STRENGTH: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE CONSTITUTION
                                    }else if ((inputLine.equalsIgnoreCase("Constitution") || inputLine.equalsIgnoreCase("CON")) && !con){
                                        con = true;
                                        writer.println("What would you like to change the constitution score to? Please note that character constitution has to be number.");
                                        writer.println("Enter CONSTITUTION: ");
                                    }else if (con){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            con = false;
                                            updateCharacter = false;
                                            writer.println(db.updateConstitution(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid constitution.");
                                            writer.println("Try Again. Enter CONSTITUTION: ");
                                        }


                                   //CHECK IF THEY WANT TO CHANGE DEXTERITY
                                    }else if ((inputLine.equalsIgnoreCase("Dexterity") || inputLine.equalsIgnoreCase("DEX")) && !dex){
                                        dex = true;
                                        writer.println("What would you like to change the dexterity score to? Please note that character dexterity has to be number.");
                                        writer.println("Enter DEXTERITY: ");
                                    }else if (dex){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            dex = false;
                                            updateCharacter = false;
                                            writer.println(db.updateDexterity(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid dexterity score.");
                                            writer.println("Try Again. Enter DEXTERITY: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE WISDOM
                                    }else if ((inputLine.equalsIgnoreCase("Wisdom") || inputLine.equalsIgnoreCase("WIS")) && !wis){
                                        wis = true;
                                        writer.println("What would you like to change the wisdom score to? Please note that character wisdom has to be number.");
                                        writer.println("Enter WISDOM: ");
                                    }else if (wis){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            wis = false;
                                            updateCharacter = false;
                                            writer.println(db.updateWisdom(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid wisdom score.");
                                            writer.println("Try Again. Enter WISDOM: ");
                                        }


                                   //THEN CHECK IF THEY WANT TO CHANGE INTELLIGENCE
                                    }else if ((inputLine.equalsIgnoreCase("Intelligence") || inputLine.equalsIgnoreCase("INT")) && !theInt){
                                        theInt = true;
                                        writer.println("What would you like to change the intelligence score to? Please note that character intelligence has to be number.");
                                        writer.println("Enter INTELLIGENCE: ");
                                    }else if (theInt){
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try{
                                                number = Integer.parseInt(inputLine.trim());
                                            }catch (NumberFormatException e){
                                                e.printStackTrace();
                                            }
                                            theInt = false;
                                            updateCharacter = false;
                                            writer.println(db.updateIntelligence(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        }else{
                                            writer.println("That is not a valid intelligence score.");
                                            writer.println("Try Again. Enter INTELLIGENCE: ");
                                        }

                                   //CHECK IF THEY WANT TO CHANGE CHARISMA
                                    }else if (inputLine.equalsIgnoreCase("Charisma") || inputLine.equalsIgnoreCase("CHA")&& !cha){
                                        cha = true;
                                        writer.println("What would you like to change the charisma score to? Please note that character charisma has to be number.");
                                        writer.println("Enter CHARISMA: ");
                                    }else if (cha) {
                                        if (isInteger(inputLine.trim())) {
                                            int number = 0;
                                            try {
                                                number = Integer.parseInt(inputLine.trim());
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }
                                            cha = false;
                                            updateCharacter = false;
                                            writer.println(db.updateCharisma(id, number) + nl + db.viewCharacter(id));
                                            writer.println("A = Create | B = View | C = Update | D = Delete");
                                        } else {
                                            writer.println("That is not a valid charisma score.");
                                            writer.println("Try Again. Enter CHARISMA: ");
                                        }

                                    }else{
                                        updateCharacter = false;
                                        writer.println("The server does not understand the request, or cannot execute that request.");
                                        writer.println("A = Create | B = View | C = Update | D = Delete");
                                    }


                        //IF THEY CHOSE TO DELETE A CHARACTER
                                }else if (deleteCharacter) {
                                    if (isInteger(inputLine.trim())) {
                                        writer.println(db.deleteRecord(Integer.parseInt(inputLine.trim())));
                                        deleteCharacter = false;
                                    }else{
                                        writer.println("That is not a valid ID number. The server cannot retrieve any records.");
                                    }
                                    writer.println("A = Create | B = View | C = Update | D = Delete");


                        //IF THE INPUT IS UNEXPECTED (NOT A,B,C,D OR DIFFERENT FROM THE EXPECTED PROMPT)
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

    //METHOD TO CHECK IF STRINGS ARE ABLE TO CONVERT TO INTEGER
    public static boolean isInteger(String str){
        try{
            Integer.parseInt(str.trim());
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }

    //METHOD TO CHECK IF STRINGS ARE ABLE TO CONVERT TO DOUBLE VALUE
    public static boolean isDouble(String str){
        try{
            Double.parseDouble(str.trim());
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }

}
