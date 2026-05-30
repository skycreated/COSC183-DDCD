import java.sql.*;


public class Database {

    //need URL, username, and password
    String url = "jdbc:mysql://localhost:3306/character";
    //normally you would need IP address for the system, but we are using our local system
    String username = "root";
    String password = "letmeinPLEASE";


    //GET ALL RECORDS IN THE DATABASE
    public void getAllData(String sql){
        try{
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database..." + conn);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){ //while it has NOT reached the end of the record list
            int id = rs.getInt("CharacterID");
            String name = rs.getString("Name");
            String className = rs.getString("Class");
            String race = rs.getString("Race");
            int level =  rs.getInt("Level");
            String alignment =  rs.getString("Alignment");
            String background = rs.getString("Background");
            int armorClass =  rs.getInt("AC");
            int HP = rs.getInt("HP");
            int speed = rs.getInt("Speed");
            int age  = rs.getInt("Age");
            double weight = rs.getDouble("Weight");
            double height = rs.getDouble("Height");

            int strength = rs.getInt("Strength");
            int dexterity = rs.getInt("Dexterity");
            int constitution = rs.getInt("Constitution");
            int intelligence = rs.getInt("Intelligence");
            int wisdom = rs.getInt("Wisdom");
            int charisma = rs.getInt("Charisma");

            System.out.println("\n------------CHARACTER DETAILS------------");
            //note this will print EVERY character record in the database
            System.out.println("ID: "+id
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
        }

        conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //TO VIEW A SPECIFIC CHARACTER BY PROVIDED ID
    public void viewCharacter(int id){
        try{
            Connection conn =  DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database..." + conn);
            String query = "SELECT CharacterID, Name, Class, Race, Level, Alignment, Background, AC, HP, Speed, Age, Weight, Height, Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma FROM characters WHERE CharacterID = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    int characterID = rs.getInt("CharacterID");
                    String name = rs.getString("Name");
                    String className = rs.getString("Class");
                    String race = rs.getString("Race");
                    int level =  rs.getInt("Level");
                    String alignment= rs.getString("Alignment");
                    String background = rs.getString("Background");
                    int armorClass =  rs.getInt("AC");
                    int HP = rs.getInt("HP");
                    int speed = rs.getInt("Speed");
                    int age = rs.getInt("Age");
                    double weight = rs.getDouble("Weight");
                    double height = rs.getDouble("Height");
                    int strength = rs.getInt("Strength");
                    int dexterity = rs.getInt("Dexterity");
                    int constitution = rs.getInt("Constitution");
                    int intelligence = rs.getInt("Intelligence");
                    int wisdom = rs.getInt("Wisdom");
                    int charisma = rs.getInt("Charisma");


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
                }else{
                    System.out.println("There is no record of a character with ID # "+id+".");
                }
            }

            conn.close();

        } catch (Exception e){
            e.printStackTrace();
        }
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
    public void updateLevel(int id, int level) {
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
    }


    //CHANGE NAME OF EXISTING CHARACTER
    public void updateName(int id, String name) {
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
    }

    //CHANGE NAME OF EXISTING CHARACTER
    public void updateClass(int id, String cClass) {
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
    }

    //CHANGE NAME OF EXISTING CHARACTER
    public void updateRace(int id, String race) {
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
    }

    //CHANGE NAME OF EXISTING CHARACTER
    public void updateAlignment(int id, String alignment) {
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
    }

    //CHANGE NAME OF EXISTING CHARACTER
    public void updateBackground(int id, String background) {
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
    }

    public void updateAC(int id, int AC) {
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
            System.out.println("Select A for Create New Random Character | Select B for View Character | Select C for Update Character | Select D for Delete Character");
            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    //DELETE A CHARACTER BY PROVIDED ID
    public void deleteRecord(int id){
        try{
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database..." + conn);

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM characters WHERE CharacterID = ?");
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();

            if(rowsDeleted > 0){
                System.out.println("Successful Deletion. Character "+id+" has been deleted.");
            }else {
                System.out.println("No character record found. This character does not exist, or it has already been deleted.");
            }

            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
