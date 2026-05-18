import java.sql.*;

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
        }

        conn.close();

    }catch(Exception e){
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
            System.out.println("Character has been deleted");
        }else {
            System.out.println("No character record found");
        }
        conn.close();

    }catch(Exception e){
        e.printStackTrace();
    }
}




}
