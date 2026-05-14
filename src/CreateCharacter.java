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
        //add code here
    public void createCharacterID(){

    }

    //CREATE NAME
    public void randomName(){
        String[]firstNames = {"Castiel","Thea","Baylor","Alistair","Marley","Joziah","Aitana","Angel","Braylen","Finn","Maximiliano","Jenesis","Korbin","Edgar","Grey","Esther","Leighton","Saylor","Reginald","Alora","Zayden","Nathanael","Silas","Aurelia","Lorelei","William","Rafael","Xander","Amoura","Nicolas","Jasper","Ernesto","Diego","Titus","Nova","Heath","Rudy","Yamileth","Thaddeus","Addison","Cesar","Eliezer","Alaric","Cillian"};
        String[]lastNames = {"King","Hail","Storm","Price","Brewer","Greene","Whitewood","Bowman","Conway","Graves","Rice","Rose","Wiggins","Ward","Gray","Snow","Moody","Webb","Berry","Bates","Molina","McCoy","Holt","Quinn","Solis","Day","Rowe","Young","May","Armstrong","Grimes","Holmes","Best","Wise","Grant","Bell","Branch","Huffman","Winters"};
        String[]titles = {"the Ruler","the Artist","the Conquerer","the Lonely One","the Brute","the Peasant","the Champion","the Knight","the Listener","the Mighty","the Blessed","the Lover","the Virtuous"};

        Random randName = new Random();
        String firstName = firstNames[randName.nextInt(firstNames.length)];
        String lastName = lastNames[randName.nextInt(lastNames.length)];
        String title = titles[randName.nextInt(titles.length)];

        characterName = firstName+" "+lastName +" "+title;
    }

    //CREATE CLASS
    public void randomClass(){
        String[]classes = {"Barbarian","Bard","Cleric","Druid","Fighter","Monk","Paladin","Ranger","Rogue","Sorcerer","Warlock","Wizard"};
        Random randClass = new Random();

        characterClass = classes[randClass.nextInt(classes.length)];
    }

    //CREATE RACE
    public void randomRace(){

    }

    //LEVEL IS 1 UNLESS CLIENT UPDATES
    int maxLevel = 20;
    //add logic here

    //ADD ALIGNMENT
    public void randomAlignment(){
        String[]alignments = {"Lawful Good","Neutral Good","Chaotic Good","Lawful Neutral","True Neutral","Chaotic Neutral","Lawful Evil","Neutral Evil","Chaotic Evil"};
        Random randAlignment = new Random();

        alignment = alignments[randAlignment.nextInt(alignments.length)];
    }

    //ADD BACKGROUND
    public void randomBackground(){

    }

    //CALCULATE AC
    public void calculateAC(){

    }

    //CALCULATE HP
    public void calculateHP(){
    }

    //ADD SPEED
    public void movementSpeed(){

    }

    //CALCULATE AGE
    public void randomAge(){

    }

    //CALCULATE WEIGHT
    public void randomWeight(){

    }

    //CALCULATE HEIGHT
    public void randomHeight(){

    }

    //CALCULATE MAIN STATISTICS
    public void calculateMainStats(){

    }



}
