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
            System.out.println("Hello client! \nSelect A for Create New Random Character | Select B for View Character | Select C for Update Character | Select D for Delete Character");
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

                } else if (message.equalsIgnoreCase("B")) { //if they choose to view a character
                    System.out.println("Enter the ID of the character you wish to view, or 'all' if you wish to view all character records: ");
                    message = scanner.nextLine();

                        if (message.equalsIgnoreCase("all")) {
                            db.getAllData("select * from characters");
                        } else{
                            db.viewCharacter(Integer.parseInt(message.trim()));
                        }

                        System.out.println("Select A for Create New Random Character | Select B for View Character | Select C for Update Character | Select D for Delete Character");



                } else if (message.equalsIgnoreCase("C")) { //if they choose to update an existing character
                            int number;
                            double dnumber;
                            System.out.println("Please enter the ID of the character you wish to UPDATE: ");
                                message = scanner.nextLine();
                                db.viewCharacter(Integer.parseInt(message.trim()));//makes sure the ID is an Integer (NOT String)

                            int id = Integer.parseInt(message.trim()); //id is a number

                            System.out.println("What field would you like to change for character " + message.trim() + "?" +
                                    "\nPlease note that you cannot change character ID numbers.\nEnter Field: ");
                            message = scanner.nextLine();


                            if (message.equalsIgnoreCase("ID")) { //if they want to change the ID we won't allow that
                                System.out.println("You cannot change character ID numbers...Try Again");

                            } else if (message.equalsIgnoreCase("level")) { //if they want to change the level
                                System.out.println("Characters can be level 1-20. What level would you like this character to be?");
                                System.out.println("Please note that changing the level may adjust the main stats of this character to reflect the level increase/decrease.");
                                System.out.println("Enter a Level: ");
                                message = scanner.nextLine();
                                    try {
                                        number = Integer.parseInt(message); //make sure it is an Integer
                                        if (number >= 1 && number <= 20) { //level must be between 1-20
                                            System.out.println("This input is valid.");
                                            db.updateLevel(id, number);
                                            db.viewCharacter(id);
                                        } else { //if they enter invalid number
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                    } catch (NumberFormatException e) {
                                        //this is to handle if that the Integer is actually valid or not
                                        System.out.println("Please enter a valid Level between 1-20.");
                                    }

                            } else if (message.equalsIgnoreCase("name")) {
                                System.out.println("What would you like to name character " + id + "?");
                                message = scanner.nextLine();
                                db.updateName(id, message);
                                db.viewCharacter(id);
                            } else if (message.equalsIgnoreCase("Class")) {
                                System.out.println("What Class do you want to assign to character " + id + "?");
                                System.out.println("Choose ONE of the following options: Barbarian | Bard | Cleric | Druid | Fighter | Monk | Paladin | Ranger | Rogue | Sorcerer | Warlock | Wizard");
                                message = scanner.nextLine();
                                db.updateClass(id, message);
                                db.viewCharacter(id);
                            } else if (message.equalsIgnoreCase("race")) {
                                System.out.println("What race do you want to assign to character " + id + "?");
                                message = scanner.nextLine();
                                db.updateRace(id, message);
                                db.viewCharacter(id);
                            } else if(message.equalsIgnoreCase("Alignment")) {
                                System.out.println("What alignment would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                db.updateAlignment(id, message);
                                db.viewCharacter(id);
                            } else if (message.equalsIgnoreCase("Background")) {
                                System.out.println("What background would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                db.updateBackground(id, message);
                                db.viewCharacter(id);
                            } else if (message.equalsIgnoreCase("AC") || message.equalsIgnoreCase("ArmorClass") || message.equalsIgnoreCase("Armor Class")){
                                System.out.println("What AC would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message); //make sure it is an Integer
                                db.updateAC(id, number);
                                db.viewCharacter(id);
                            } else if(message.equalsIgnoreCase("HP") || message.equalsIgnoreCase("Hit Points")){
                                System.out.println("What HP would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateHP(id, number);
                                db.viewCharacter(id);
                            }else if(message.equalsIgnoreCase("Speed")){
                                System.out.println("What Speed (in feet) would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateSpeed(id, number);
                                db.viewCharacter(id);
                            } else if(message.equalsIgnoreCase("Age")){
                                System.out.println("What age (in years) would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateAge(id, number);
                                db.viewCharacter(id);
                            } else if(message.equalsIgnoreCase("Weight")){
                                System.out.println("What weight (in pounds) would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                dnumber = Double.parseDouble(message);
                                db.updateWeight(id, dnumber);
                                db.viewCharacter(id);
                            }else if(message.equalsIgnoreCase("Height")){
                                System.out.println("What height (in feet) would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                dnumber = Double.parseDouble(message);
                                db.updateHeight(id, dnumber);
                                db.viewCharacter(id);
                            }else if(message.equalsIgnoreCase("Strength") || message.equalsIgnoreCase("STR")){
                                System.out.println("What Strength score would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateStrength(id, number);
                                db.viewCharacter(id);
                            }else if (message.equalsIgnoreCase("Dexterity") || message.equalsIgnoreCase("DEX")){
                                System.out.println("What Dexterity score would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateDexterity(id, number);
                                db.viewCharacter(id);
                            }else if(message.equalsIgnoreCase("Intelligence") || message.equalsIgnoreCase("INT")){
                                System.out.println("What Intelligence score would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateIntelligence(id, number);
                                db.viewCharacter(id);
                            }else if(message.equalsIgnoreCase("Wisdom") || message.equalsIgnoreCase("WIS")){
                                System.out.println("What Wisdom score would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateWisdom(id, number);
                                db.viewCharacter(id);
                            }else if(message.equalsIgnoreCase("Charisma") || message.equalsIgnoreCase("CHA")){
                                System.out.println("What Charisma score would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateCharisma(id, number);
                                db.viewCharacter(id);
                            }else if(message.equalsIgnoreCase("Constitution") || message.equalsIgnoreCase("CON")){
                                System.out.println("What Constitution score would you like to assign to character " + id + "?");
                                message = scanner.nextLine();
                                number = Integer.parseInt(message);
                                db.updateConstitution(id, number);
                                db.viewCharacter(id);
                            }else{
                                System.out.println("Invalid input...");
                            }


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
