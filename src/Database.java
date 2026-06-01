import java.sql.*;
import java.util.ArrayList;


public class Database {

    //need URL, username, and password
    String url = "jdbc:mysql://localhost:3306/character";
    //normally you would need IP address for the system, but we are using our local system
    String username = "root";
    String password = "letmeinPLEASE";

    String name = "";
    int characterID = 0;
    String className = "";
    String race = "";
    int level = 0;
    String alignment = "";
    String background = "";
    int armorClass = 0;
    int HP = 0;
    int speed = 0;
    int age = 0;
    double weight = 0;
    double height = 0;
    int strength = 0;
    int dexterity = 0;
    int constitution = 0;
    int intelligence = 0;
    int wisdom = 0;
    int charisma = 0;

    public boolean noRecord = false;
    String message = "";

    //GET ALL RECORDS IN THE DATABASE
    public String getAllData() {

        ArrayList<String> allCharacters = new ArrayList();
        StringBuilder sb = new StringBuilder();

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database..." + conn);
            String sql = "SELECT * FROM characters";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) { //while it has NOT reached the end of the record list
                characterID = rs.getInt("CharacterID");
                name = rs.getString("Name");
                className = rs.getString("Class");
                race = rs.getString("Race");
                level = rs.getInt("Level");
                alignment = rs.getString("Alignment");
                background = rs.getString("Background");
                armorClass = rs.getInt("AC");
                HP = rs.getInt("HP");
                speed = rs.getInt("Speed");
                age = rs.getInt("Age");
                weight = rs.getDouble("Weight");
                height = rs.getDouble("Height");

                strength = rs.getInt("Strength");
                dexterity = rs.getInt("Dexterity");
                constitution = rs.getInt("Constitution");
                intelligence = rs.getInt("Intelligence");
                wisdom = rs.getInt("Wisdom");
                charisma = rs.getInt("Charisma");

                ArrayList<String> na = new ArrayList();
                na.add(String.valueOf(characterID));
                na.add(name);
                na.add(className);
                na.add(race);
                na.add(String.valueOf(level));
                na.add(alignment);
                na.add(background);
                na.add(String.valueOf(armorClass));
                na.add(String.valueOf(HP));
                na.add(String.valueOf(speed));
                na.add(String.valueOf(age));
                na.add(String.valueOf(weight));
                na.add(String.valueOf(height));
                na.add(String.valueOf(strength));
                na.add(String.valueOf(dexterity));
                na.add(String.valueOf(constitution));
                na.add(String.valueOf(intelligence));
                na.add(String.valueOf(wisdom));
                na.add(String.valueOf(charisma));

                allCharacters.add(na.toString());

            /*
            System.out.println("\n------------CHARACTER DETAILS------------");
            //note this will print EVERY character record in the database
            System.out.println("ID: "+characterID
                    +"\nName: "+ name
                    +"\nClass: "+className
                    +"\nRace: "+race
                    +"\nLevel: "+level
                    +"\nAlignment: "+alignment
                    +"\nBackground: "+background
                    +"\nAC: "+armorClass
                    +"\nHP: "+HP
                    +"\nSpeed: "+speed
                    +"\nAge: "+age
                    +"\nWeight: "+weight
                    +"\nHeight: "+height
                    +"\nStrength: "+strength
                    +"\nDexterity: "+dexterity
                    +"\nConstitution: "+constitution
                    +"\nIntelligence: "+intelligence
                    +"\nWisdom: "+wisdom
                    +"\nCharisma: "+charisma
                    +"\n-----------------------------------------");

             */


            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

                /*"------------CHARACTER DETAILS------------\nID: "+characterID
                +"\nName: "+ name
                +"\nClass: "+className
                +"\nRace: "+race
                +"\nLevel: "+level
                +"\nAlignment: "+alignment
                +"\nBackground: "+background
                +"\nAC: "+armorClass
                +"\nHP: "+HP
                +"\nSpeed: "+speed
                +"\nAge: "+age
                +"\nWeight: "+weight
                +"\nHeight: "+height
                +"\nStrength: "+strength
                +"\nDexterity: "+dexterity
                +"\nConstitution: "+constitution
                +"\nIntelligence: "+intelligence
                +"\nWisdom: "+wisdom
                +"\nCharisma: "+charisma
                +"\n-----------------------------------------";

                 */
        System.out.println("Retrieving all characters from database...");
        for  (int i = 0; i < allCharacters.size(); i++) {
            //System.out.println(allCharacters.get(i));
            sb.append(allCharacters.get(i));
            sb.append('\n');
        }
        System.out.println("All available characters have been retrieved and sent to client");
        return sb.toString();
    }


    //TO VIEW A SPECIFIC CHARACTER BY PROVIDED ID
    public String viewCharacter(int id){

        try{
            Connection conn =  DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database..." + conn);
            String query = "SELECT CharacterID, Name, Class, Race, Level, Alignment, Background, AC, HP, Speed, Age, Weight, Height, Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma FROM characters WHERE CharacterID = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    noRecord = false;
                    characterID = rs.getInt("CharacterID");
                    name = rs.getString("Name");
                    className = rs.getString("Class");
                    race = rs.getString("Race");
                    level =  rs.getInt("Level");
                    alignment= rs.getString("Alignment");
                    background = rs.getString("Background");
                    armorClass =  rs.getInt("AC");
                    HP = rs.getInt("HP");
                    speed = rs.getInt("Speed");
                    age = rs.getInt("Age");
                    weight = rs.getDouble("Weight");
                    height = rs.getDouble("Height");
                    strength = rs.getInt("Strength");
                    dexterity = rs.getInt("Dexterity");
                    constitution = rs.getInt("Constitution");
                    intelligence = rs.getInt("Intelligence");
                    wisdom = rs.getInt("Wisdom");
                    charisma = rs.getInt("Charisma");


                }else{
                    System.out.println("There is no record of a character with ID # "+id+".");
                    noRecord = true;
                }
            }

            conn.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return summary();
    }



    public String summary(){
        if (noRecord) {
            message = "There are no records with that ID in the database. Sorry.";
        }else {
           message = "Please note if the character record shows 0, then there is no record present in the database (which means that record does not exist)" +
                    "\nPrinting Record...."+
                    "\n------------CHARACTER DETAILS------------" +
                    "\nID: "+characterID
                    +"\nName: "+ name
                    +"\nClass: "+className
                    +"\nRace: "+race
                    +"\nLevel: "+level
                    +"\nAlignment: "+alignment
                    +"\nBackground: "+background
                    +"\nAC: "+armorClass
                    +"\nHP: "+HP
                    +"\nSpeed: "+speed
                    +"\nAge: "+age
                    +"\nWeight: "+weight
                    +"\nHeight: "+height
                    +"\nStrength: "+strength
                    +"\nDexterity: "+dexterity
                    +"\nConstitution: "+constitution
                    +"\nIntelligence: "+intelligence
                    +"\nWisdom: "+wisdom
                    +"\nCharisma: "+charisma
                    +"\n-----------------------------------------";
        }
        return message;
    }


    //UPDATE CHARACTER BASED ON PROVIDED ID
    public boolean updateCharacter(int id, String name, int strength, int dexterity,
                                               int constitution, int intelligence,
                                               int wisdom, int charisma) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE `characters` " +
                    "SET Name = ?, Strength = ?, Dexterity = ?, Constitution = ?, " +
                    "Intelligence = ?, Wisdom = ?, Charisma = ? " +
                    "WHERE CharacterID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setInt(2, strength);
            stmt.setInt(3, dexterity);
            stmt.setInt(4, constitution);
            stmt.setInt(5, intelligence);
            stmt.setInt(6, wisdom);
            stmt.setInt(7, charisma);
            stmt.setInt(8, id);

            int rowsUpdated = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //CHANGE LEVEL OF EXISTING CHARACTER
    public String updateLevel(int id, int level) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Level = ? WHERE CharacterID = ?");

            stmt.setInt(1, level);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Level has been updated for character " + id;
    }


    //CHANGE NAME OF EXISTING CHARACTER
    public String updateName(int id, String name) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Name = ? WHERE CharacterID = ?");

            stmt.setString(1, name);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Name has been updated for character " + id;
    }

    //CHANGE CLASS OF EXISTING CHARACTER
    public String updateClass(int id, String cClass) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Class = ? WHERE CharacterID = ?");

            stmt.setString(1, cClass);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Class has been updated for character "+id;
    }

    //CHANGE RACE OF EXISTING CHARACTER
    public String updateRace(int id, String race) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Race = ? WHERE CharacterID = ?");

            stmt.setString(1, race);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Race has been updated for character "+id;
    }

    //CHANGE ALIGNMENT OF EXISTING CHARACTER
    public String updateAlignment(int id, String alignment) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Alignment = ? WHERE CharacterID = ?");

            stmt.setString(1, alignment);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Alignment has been updated for character "+id;
    }

    //CHANGE BACKGROUND OF EXISTING CHARACTER
    public String updateBackground(int id, String background) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Background = ? WHERE CharacterID = ?");

            stmt.setString(1, background);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Background has been updated for character "+id;
    }

    //UPDATE AC OF EXISTING CHARACTER
    public String updateAC(int id, int AC) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET AC = ? WHERE CharacterID = ?");

            stmt.setInt(1, AC);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "AC has been updated for character "+id;
    }

    //UPDATE HP OF EXISTING CHARACTER
    public String updateHP(int id, int HP) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET HP = ? WHERE CharacterID = ?");

            stmt.setInt(1, HP);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "HP has been updated for character "+id;
    }

    //UPDATE STRENGTH OF EXISTING CHARACTER
    public String updateStrength(int id, int strength) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Strength = ? WHERE CharacterID = ?");

            stmt.setInt(1, strength);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Strength has been updated for character "+id;
    }

    //UPDATE DEXTERITY OF EXISTING CHARACTER
    public String updateDexterity(int id, int DEX) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Dexterity = ? WHERE CharacterID = ?");

            stmt.setInt(1, DEX);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Dexterity has been updated for character "+id;
    }

    //UPDATE CONSTITUTION OF EXISTING CHARACTER
    public String updateConstitution(int id, int CON) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Constitution = ? WHERE CharacterID = ?");

            stmt.setInt(1, CON);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Constitution has been updated for character "+id;
    }


    //UPDATE INTELLIGENCE OF EXISTING CHARACTER
    public String updateIntelligence(int id, int Intelligence) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Intelligence = ? WHERE CharacterID = ?");

            stmt.setInt(1, Intelligence);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Intelligence has been updated for character "+id;
    }


    //UPDATE WISDOM OF EXISTING CHARACTER
    public String updateWisdom(int id, int WIS) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Wisdom = ? WHERE CharacterID = ?");

            stmt.setInt(1, WIS);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Wisdom has been updated for character "+id;
    }


    //UPDATE CHARISMA OF EXISTING CHARACTER
    public String updateCharisma(int id, int charisma) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Charisma = ? WHERE CharacterID = ?");

            stmt.setInt(1, charisma);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Charisma has been updated for character "+id;
    }

    //UPDATE SPEED OF EXISTING CHARACTER
    public String updateSpeed(int id, int speed) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Speed = ? WHERE CharacterID = ?");

            stmt.setInt(1, speed);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Speed has been updated for character "+id;
    }


    //UPDATE AGE OF EXISTING CHARACTER
    public String updateAge(int id, int age) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Age = ? WHERE CharacterID = ?");

            stmt.setInt(1, age);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Age has been updated for character "+id;
    }


    //UPDATE WEIGHT OF EXISTING CHARACTER
    public String updateWeight(int id, double weight) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Weight = ? WHERE CharacterID = ?");

            stmt.setDouble(1, weight);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Weight has been updated for character "+id;
    }


    //UPDATE HEIGHT OF EXISTING CHARACTER
    public String updateHeight(int id, double height) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established..." + conn);
            PreparedStatement stmt =  conn.prepareStatement("UPDATE characters SET Height = ? WHERE CharacterID = ?");

            stmt.setDouble(1, height);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Height has been updated for character "+id;
    }



    //INSERT A RANDOMLY GENERATED CHARACTER INTO THE DATABASE
    public void insertCharacters
        (int id, String name, String className, String race, int level, String alignment,
        String background, int armourClass, int HP, int speed, int age, double weight,
        double height, int strength, int dexterity, int constitution, int intelligence,
        int wisdom, int charisma) {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database..." + conn);

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CHARACTERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, className);
            stmt.setString(4, race);
            stmt.setInt(5, level);
            stmt.setString(6, alignment);
            stmt.setString(7, background);
            stmt.setInt(8, armourClass);
            stmt.setInt(9, HP);
            stmt.setInt(10, speed);
            stmt.setInt(11, age);
            stmt.setDouble(12, weight);
            stmt.setDouble(13, height);
            stmt.setInt(14, strength);
            stmt.setInt(15,dexterity);
            stmt.setInt(16, constitution);
            stmt.setInt(17, intelligence);
            stmt.setInt(18, wisdom);
            stmt.setInt(19, charisma);

            stmt.execute();

            System.out.println("Data has been inserted...");
            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    //DELETE A CHARACTER BY PROVIDED ID
    public String deleteRecord(int id){
        try{
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database..." + conn);

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM characters WHERE CharacterID = ?");
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();

            if(rowsDeleted > 0){
                System.out.println("Successful Deletion. Character "+id+" has been deleted.");
                characterID = id;
            }else {
                System.out.println("No character record found. This character does not exist, or it has already been deleted.");
                noRecord = true;
            }

            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return deletionSummary();
    }


    public String deletionSummary(){
        if(noRecord){
           message = "There was no record found with that ID in the database. Sorry.";
        }else{
           message = "Character "+characterID+" has been successfully deleted.";
        }
        return message;
    }


}
