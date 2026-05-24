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

    int mod = 0;
    int dexmod = 0;
    int wismod = 0;

    String armour;

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
    public int calculateAC(){
        switch (characterClass){
            case "Barbarian","Cleric","Druid":
                armour = "M";
                break;
            case "Bard","Ranger","Rogue":
                armour = "L";
                break;
            case "Fighter","Paladin":
                armour = "H";
                break;
            case "Monk":
                AC = 10+getDexModifier()+getDexModifier();
                break;
            case "Warlock","Wizard","Sorcerer":
                AC = 10+getDexModifier();

        }
        return AC;
    }

    //CALCULATE HP
    public void calculateHP(){
        if (level == 1){
            switch (characterClass){
                case "Barbarian":
                    HP = 12+getConModifier();
                    break;
                case "Bard", "Cleric", "Druid","Monk","Rogue":
                    HP = 8+getConModifier();
                    break;
                case "Fighter","Paladin","Ranger","Warlock":
                    HP=10+getConModifier();
                    break;
                case "Sorcerer","Wizard":
                    HP=6+getConModifier();
                    break;
            }
        }else {
            switch (characterClass){
                case "Barbarian":
                    HP = (7+getConModifier())*level;
                    break;
                case "Bard", "Cleric", "Druid","Monk","Rogue":
                    HP = (5+getConModifier())*level;
                    break;
                case "Fighter","Paladin","Ranger","Warlock":
                    HP=(6+getConModifier())*level;
                    break;
                case "Sorcerer","Wizard":
                    HP=(4+getConModifier())*level;
                    break;
            }
        }

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
        int[]standardArray = {15,14,13,12,10,8};
        switch (characterClass){
            case "Barbarian":
                strength = standardArray[0];
                constitution = standardArray[1];
                dexterity = standardArray[2];
                intelligence = standardArray[3];
                wisdom = standardArray[4];
                charisma = standardArray[5];
                break;
            case "Bard":
                charisma = standardArray[0];
                dexterity = standardArray[1];
                constitution = standardArray[2];
                intelligence = standardArray[3];
                wisdom = standardArray[4];
                strength = standardArray[5];
                break;
            case "Cleric":
                wisdom = standardArray[0];
                constitution = standardArray[1];
                strength = standardArray[2];
                charisma = standardArray[3];
                dexterity = standardArray[4];
                intelligence = standardArray[5];
                break;
            case "Druid":
                wisdom = standardArray[0];
                constitution = standardArray[1];
                dexterity = standardArray[2];
                strength = standardArray[3];
                charisma = standardArray[4];
                intelligence = standardArray[5];
                break;
            case "Fighter":
                strength = standardArray[0];
                intelligence = standardArray[1];
                constitution = standardArray[2];
                dexterity = standardArray[3];
                wisdom = standardArray[4];
                charisma = standardArray[5];
                break;
            case "Monk":
                dexterity = standardArray[0];
                wisdom = standardArray[1];
                constitution = standardArray[2];
                strength = standardArray[3];
                charisma = standardArray[4];
                intelligence = standardArray[5];
                break;
            case "Paladin":
                strength = standardArray[0];
                charisma = standardArray[1];
                constitution = standardArray[2];
                dexterity = standardArray[3];
                intelligence = standardArray[4];
                wisdom = standardArray[5];
                break;
            case "Ranger":
                dexterity = standardArray[0];
                wisdom = standardArray[1];
                constitution = standardArray[2];
                strength = standardArray[3];
                intelligence = standardArray[4];
                charisma = standardArray[5];
                break;
            case "Rogue":
                dexterity = standardArray[0];
                charisma = standardArray[1];
                intelligence = standardArray[2];
                constitution = standardArray[3];
                strength = standardArray[4];
                wisdom = standardArray[5];
                break;
            case "Sorcerer":
                charisma = standardArray[0];
                constitution = standardArray[1];
                wisdom = standardArray[2];
                intelligence = standardArray[3];
                dexterity = standardArray[4];
                strength = standardArray[5];
                break;
            case "Warlock":
                charisma = standardArray[0];
                constitution = standardArray[1];
                dexterity = standardArray[2];
                wisdom = standardArray[3];
                intelligence = standardArray[4];
                strength = standardArray[5];
                break;
            case "Wizard":
                intelligence = standardArray[0];
                constitution = standardArray[1];
                dexterity = standardArray[2];
                charisma = standardArray[3];
                wisdom = standardArray[4];
                strength = standardArray[5];
                break;
        }
    }

    public int getConModifier(){
        switch (constitution){
            case 1:
                mod = -5;
                break;
            case 2,3:
                mod = -4;
                break;
            case 4,5:
                mod = -3;
                break;
            case 6,7:
                mod = -2;
                break;
            case 8,9:
                mod = -1;
                break;
            case 10,11:
                mod = 0;
                break;
            case 12,13:
                mod = 1;
                break;
            case 14,15:
                mod = 2;
                break;
            case 16,17:
                mod = 3;
                break;
            case 18,19:
                mod = 4;
                break;
            case 20,21:
                mod = 5;
                break;
            case 22,23:
                mod = 6;
                break;
            case 24,25:
                mod = 7;
                break;
            case 26,27:
                mod = 8;
                break;
            case 28,29:
                mod = 9;
                break;
            case 30,31:
                mod = 10;
                break;
        }
        return mod;
    }

    public int getDexModifier(){
        switch (dexterity){
            case 1:
                dexmod = -5;
                break;
            case 2,3:
                dexmod = -4;
                break;
            case 4,5:
                dexmod = -3;
                break;
            case 6,7:
                dexmod = -2;
                break;
            case 8,9:
                dexmod = -1;
                break;
            case 10,11:
                dexmod = 0;
                break;
            case 12,13:
                dexmod = 1;
                break;
            case 14,15:
                dexmod = 2;
                break;
            case 16,17:
                dexmod = 3;
                break;
            case 18,19:
                dexmod = 4;
                break;
            case 20,21:
                dexmod = 5;
                break;
            case 22,23:
                dexmod = 6;
                break;
            case 24,25:
                dexmod = 7;
                break;
            case 26,27:
                dexmod = 8;
                break;
            case 28,29:
                dexmod = 9;
                break;
            case 30,31:
                dexmod = 10;
                break;
        }
        return dexmod;
    }

    public int getWisModifier(){
        switch (wisdom){
            case 1:
                wismod = -5;
                break;
            case 2,3:
                wismod = -4;
                break;
            case 4,5:
                wismod = -3;
                break;
            case 6,7:
                wismod = -2;
                break;
            case 8,9:
                wismod = -1;
                break;
            case 10,11:
                wismod = 0;
                break;
            case 12,13:
                wismod = 1;
                break;
            case 14,15:
                wismod = 2;
                break;
            case 16,17:
                wismod = 3;
                break;
            case 18,19:
                wismod = 4;
                break;
            case 20,21:
                wismod = 5;
                break;
            case 22,23:
                wismod = 6;
                break;
            case 24,25:
                wismod = 7;
                break;
            case 26,27:
                wismod = 8;
                break;
            case 28,29:
                wismod = 9;
                break;
            case 30,31:
                wismod = 10;
                break;
        }
        return wismod;
    }



    @Override
    public String toString(){return "The characters name is " + characterName +
            ", they are a " + alignment + " " + race + " " + characterClass +
            ". They are " + age +
            " years old, weigh " + weight +
            ", and their height is " + height;}
}
