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
    double weight;
    double height;

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
    public String randomName(){
        String[]firstNames = {"Castiel","Thea","Baylor","Alistair","Marley","Joziah","Aitana","Angel","Braylen","Finn","Maximiliano","Jenesis","Korbin","Edgar","Grey","Esther","Leighton","Saylor","Reginald","Alora","Zayden","Nathanael","Silas","Aurelia","Lorelei","William","Rafael","Xander","Amoura","Nicolas","Jasper","Ernesto","Diego","Titus","Nova","Heath","Rudy","Yamileth","Thaddeus","Addison","Cesar","Eliezer","Alaric","Cillian"};
        String[]lastNames = {"King","Hail","Storm","Price","Brewer","Greene","Whitewood","Bowman","Conway","Graves","Rice","Rose","Wiggins","Ward","Gray","Snow","Moody","Webb","Berry","Bates","Molina","McCoy","Holt","Quinn","Solis","Day","Rowe","Young","May","Armstrong","Grimes","Holmes","Best","Wise","Grant","Bell","Branch","Huffman","Winters"};
        String[]titles = {"the Ruler","the Artist","the Conquerer","the Lonely One","the Brute","the Peasant","the Champion","the Knight","the Listener","the Mighty","the Blessed","the Lover","the Virtuous"};

        Random randName = new Random();
        String firstName = firstNames[randName.nextInt(firstNames.length)];
        String lastName = lastNames[randName.nextInt(lastNames.length)];
        String title = titles[randName.nextInt(titles.length)];

        characterName = firstName+" "+lastName +" "+title;
        return characterName;
    }

    //CREATE CLASS
    public void randomClass(){
        String[]classes = {"Barbarian","Bard","Cleric","Druid","Fighter","Monk","Paladin","Ranger","Rogue","Sorcerer","Warlock","Wizard"};
        Random randClass = new Random();

        characterClass = classes[randClass.nextInt(classes.length)];
    }

    //CREATE RACE
    public String randomRace(){

        switch (characterClass){
            case "Barbarian", "Fighter":
                race = "Half-Orc";
                break;
            case "Bard":
                race = "Human";
                break;
            case "Cleric":
                race = "Dwarf";
                break;
            case "Druid":
                race = "Wood Elf";
                break;
            case "Monk":
                race = "Halfling";
                break;
            case "Paladin":
                race = "Half-Elf";
                break;
            case "Ranger":
                race = "Dragonborn";
                break;
            case "Rogue":
                race = "Gnome";
                break;
            case "Sorcerer":
                race = "Tiefling";
                break;
            case "Warlock":
                race = "Dark Elf";
                break;
            case "Wizard":
                race = "High Elf";
        }
        return race;
    }

    //LEVEL IS 1 UNLESS CLIENT UPDATES
    int maxLevel = 20;
    public int getLevel(){
        level = 1;
        return level;
    }
    //add logic here

    //ADD ALIGNMENT
    public void randomAlignment(){
        String[]alignments = {"Lawful Good","Neutral Good","Chaotic Good","Lawful Neutral","True Neutral","Chaotic Neutral","Lawful Evil","Neutral Evil","Chaotic Evil"};
        Random randAlignment = new Random();

        alignment = alignments[randAlignment.nextInt(alignments.length)];
    }

    //ADD BACKGROUND
    public String randomBackground(){

        switch (characterClass){
            case "Barbarian", "Ranger":
                background = "Outlander";
                break;
            case "Bard":
                background = "Entertainer";
                break;
            case "Cleric":
                background = "Acolyte";
                break;
            case "Druid", "Monk", "Sorcerer":
                background = "Hermit";
                break;
            case "Fighter":
                background = "Soldier";
                break;
            case "Paladin":
                background = "Noble";
                break;
            case "Rogue", "Warlock":
                background = "Charlatan";
                break;
            case "Wizard":
                background = "Sage";
                break;
        }
        return background;
    }

    //CALCULATE AC
    public void calculateAC(){

    }

    //CALCULATE HP
    public void calculateHP(){
    }

    //ADD SPEED
    public int movementSpeed(){

        switch (race){
            case "Dwarf","Halfling","Gnome":
                speed = 25;
                break;
            case "Wood Elf", "Dark Elf", "High Elf","Human","Dragonborn","Half-Elf","Half-Orc","Tiefling":
                speed = 30;
                break;
        }
        return speed;


    }

    //CALCULATE AGE
    public int randomAge(){
        switch (race){
            case "Dwarf":
                age = 200;
                break;
            case "High Elf", "Wood Elf","Dark Elf":
                age = 550;
                break;
            case "Halfling":
                age = 100;
                break;
            case "Human":
                age = 35;
                break;
            case "Dragonborn", "Half-Orc", "Tiefling":
                age = 25;
                break;
            case "Gnome":
                age = 300;
                break;
            case "Half-Elf":
                age = 125;
                break;
        }
        return age;
    }

    //CALCULATE WEIGHT
    public double randomWeight(){
        switch(race){
            case "Human", "Tiefling":
                weight = 180;
                break;
            case "Wood Elf", "Dark Elf", "High Elf":
                weight = 110;
                break;
            case "Half-Elf":
                weight = 145;
                break;
            case "Dwarf":
                weight = 160;
                break;
            case "Dragonborn":
                weight = 325;
                break;
            case "Half-Orc":
                weight = 200;
                break;
            case "Halfling", "Gnome":
                weight = 40;
                break;
        }
        return weight;
    }

    //CALCULATE HEIGHT
    public double getHeight(){

        switch(race){
            case "Human", "Wood Elf", "Dark Elf", "High Elf", "Half-Elf", "Tiefling":
                height=5.5;
                break;
            case "Dwarf":
                height = 4;
                break;
            case "Half-Orc", "Dragonborn":
                height = 6.5;
                break;
            case "Halfling":
                height = 3;
                break;
            case "Gnome":
                height = 3.25;
                break;
        }
        return height;
    }

    //CALCULATE MAIN STATISTICS
    public void calculateMainStats(){

    }



}
