import java.util.Random;

public class CreateCharacter {


    int characterID;
    String characterName;
    String characterClass;
    String race;
    int level;
    String alignment;
    String background;
    int AC;
    int HP;
    int speed;
    int age;
    int weight;
    int height;

    int strength;
    int dexterity;
    int constitution;
    int intelligence;
    int wisdom;
    int charisma;


    //CREATE ID

    //CREATE NAME
    public void randomName(){
        String[]firstNames = {"Castiel","Thea","Baylor","Alistair","Marley","Joziah","Aitana","Angel","Braylen","Finn","Maximiliano","Jenesis","Korbin","Edgar","Grey","Esther","Leighton","Saylor","Reginald","Alora","Zayden","Nathanael","Silas","Aurelia","Lorelei","William","Rafael","Xander","Amoura","Nicolas","Jasper","Ernesto","Diego","Titus","Nova","Heath","Rudy","Yamileth","Thaddeus","Addison","Cesar","Eliezer","Alaric","Cillian"};
        String[]lastNames = {"King","Hail","Storm","Price","Brewer","Greene","Whitewood","Bowman","Conway","Graves","Rice","Rose","Wiggins","Ward","Gray","Snow","Moody","Webb","Berry","Bates","Molina","McCoy","Holt","Quinn","Solis","Day","Rowe","Young","May","Armstrong","Grimes","Holmes","Best","Wise","Grant","Bell","Branch","Huffman","Winters"};
        String[]titles = {"the Ruler","the Artist","the Conquerer","the Lonely One","the Brute","the Peasant","the Champion","the Knight","the Listener","the Mighty","the Blessed","the Lover","the Virtuous"};

        Random rand = new Random();
        String firstName = firstNames[rand.nextInt(firstNames.length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        String title = titles[rand.nextInt(titles.length)];

        characterName = firstName+" "+lastName +" "+title;
    }

    //CREATE CLASS

    //CREATE RACE

    //LEVEL IS 1 UNLESS CLIENT UPDATES

    //ADD ALIGNMENT

    //ADD BACKGROUND

    //CALCULATE AC

    //CALCULATE HP

    //ADD SPEED

    //CALCULATE AGE

    //CALCULATE WEIGHT

    //CALCULATE HEIGHT

    //CALCULATE MAIN STATISTICS



}
