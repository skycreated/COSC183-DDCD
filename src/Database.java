import java.sql.*;
import java.util.List;

public class Database {


    //need URL, username, and password
    String url = "jdbc:mysql://localhost:3306/character";
    //normally you would need IP address for the system, but we are using our local system
    String username = "root";
    String password = "letmeinPLEASE";


    //GET THE RECORD BY ID
public void getData(String sql){
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
            int armourClass =  rs.getInt("AC");
            int HP = rs.getInt("HP");
            int speed = rs.getInt("Speed");
            int age  = rs.getInt("Age");
            int weight = rs.getInt("Weight");
            int height = rs.getInt("Height");

            int strength = rs.getInt("Strength");
            int dexterity = rs.getInt("Dexterity");
            int constitution = rs.getInt("Constitution");
            int intelligence = rs.getInt("Intelligence");
            int wisdom = rs.getInt("Wisdom");
            int charisma = rs.getInt("Charisma");

            System.out.println("Character Details printed here:");
            //note this will print every record in the database
            //we need to store in an array and display the character summary to the client for their specific request
            String[] allValues = {
                    String.valueOf(id),
                    name,
                    className,
                    race,
                    String.valueOf(level),
                    alignment,
                    background,
                    String.valueOf(armourClass),
                    String.valueOf(HP),
                    String.valueOf(speed),
                    String.valueOf(age),
                    String.valueOf(weight),
                    String.valueOf(height),
                    String.valueOf(strength),
                    String.valueOf(dexterity),
                    String.valueOf(constitution),
                    String.valueOf(intelligence),
                    String.valueOf(wisdom),
                    String.valueOf(charisma)
            };

        }

        conn.close();

    }catch(Exception e){
        e.printStackTrace();
    }
}

public void insertPrepStatement(int id, String name, String className, String race, int level, String alignment, String background, int armourClass, int HP, int speed, int age, int weight, int height, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma){
    String url = "jdbc:mysql://localhost:3306/character";
    String username = "root";
    String password = "letmeinPLEASE";

    try {
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to database..." + conn);

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO PRODUCTS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
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
        stmt.setInt(12, weight);
        stmt.setInt(13, height);
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


public void deleteRecord(int id){
    try{
        Connection conn = DriverManager.getConnection(url,username,password);
        System.out.println("Connected to database..." + conn);

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM character WHERE CharacterID = ?");
        stmt.setInt(1, id);

        int rowsDeleted = stmt.executeUpdate();

        if(rowsDeleted > 0){
            System.out.println("Character has been deleted.");
        }else {
            System.out.println("No character record found. This character does not exist, or it has already been deleted.");
        }
        conn.close();

    }catch(Exception e){
        e.printStackTrace();

    }
}




}
