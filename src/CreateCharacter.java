import java.util.Random;

public class CreateCharacter {

    public CreateCharacter(){

    }

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

    String[] character = new String[19];

    //CREATE ID
        //add code here
    public void createCharacterID(){
            characterID = (int)(Math.random() * 10001);
            character[0] = String.valueOf(characterID);
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
        int[]standardArray = {15,14,13,12,10,8};
        Random randClass = new Random();

        characterClass = classes[randClass.nextInt(classes.length)];

        switch (characterClass) {
            case "Barbarian" -> {
                strength = 15;
                constitution = 14;
                dexterity = 13;
                intelligence = 12;
                wisdom = 10;
                charisma = 8;
                background = "Outlander";
                race = "Half-Orc";
                height = 6.5;
                AC = 11 + getDexModifier();
                HP = 12 + getConModifier();
                speed = 30;
                age = 25;
                weight = 200;
            }
            case "Bard" -> {
                charisma = 15;
                dexterity = 14;
                constitution = 13;
                intelligence = 12;
                wisdom = 10;
                strength = 8;
                background = "Entertainer";
                race = "Human";
                height = 5.5;
                AC = 14 + getDexModifier();
                HP = 8 + getConModifier();
                speed = 30;
                age = 35;
                weight = 180;
            }
            case "Fighter" -> {
                strength = standardArray[0];
                intelligence = standardArray[1];
                constitution = standardArray[2];
                dexterity = standardArray[3];
                wisdom = standardArray[4];
                charisma = standardArray[5];
                background = "Soldier";
                race = "Half-Orc";
                height = 6.5;
                AC = 16 + getDexModifier();
                HP = 10 + getConModifier();
                speed = 30;
                age = 25;
                weight = 200;
            }
            case "Paladin" -> {
                strength = standardArray[0];
                charisma = standardArray[1];
                constitution = standardArray[2];
                dexterity = standardArray[3];
                intelligence = standardArray[4];
                wisdom = standardArray[5];
                background = "Noble";
                race = "Half-Elf";
                height = 5.5;
                AC = 16 + getDexModifier();
                HP = 10 + getConModifier();
                speed = 30;
                age = 125;
                weight = 145;
            }
            case "Ranger" -> {
                dexterity = standardArray[0];
                wisdom = standardArray[1];
                constitution = standardArray[2];
                strength = standardArray[3];
                intelligence = standardArray[4];
                charisma = standardArray[5];
                background = "Outlander";
                race = "Dragonborn";
                height = 6.5;
                AC = 14 + getDexModifier();
                HP = 10 + getConModifier();
                speed = 30;
                age = 25;
                weight = 325;
            }
            case "Wizard" -> {
                intelligence = standardArray[0];
                constitution = standardArray[1];
                dexterity = standardArray[2];
                charisma = standardArray[3];
                wisdom = standardArray[4];
                strength = standardArray[5];
                background = "Sage";
                race = "High Elf";
                height = 5.5;
                AC = 10 + getDexModifier();
                HP = 6 + getConModifier();
                speed = 30;
                age = 550;
                weight = 110;
            }
            case "Sorcerer" -> {
                charisma = standardArray[0];
                constitution = standardArray[1];
                wisdom = standardArray[2];
                intelligence = standardArray[3];
                dexterity = standardArray[4];
                strength = standardArray[5];
                background = "Hermit";
                race = "Tiefling";
                height = 5.5;
                AC = 10 + getDexModifier();
                HP = 6 + getConModifier();
                speed = 30;
                age = 25;
                weight = 180;
            }
            case "Cleric" -> {
                wisdom = standardArray[0];
                constitution = standardArray[1];
                strength = standardArray[2];
                charisma = standardArray[3];
                dexterity = standardArray[4];
                intelligence = standardArray[5];
                background = "Acolyte";
                race = "Dwarf";
                height = 4;
                AC = 11 + getDexModifier();
                HP = 8 + getConModifier();
                speed = 25;
                age = 200;
                weight = 160;
            }
            case "Druid" -> {
                wisdom = standardArray[0];
                constitution = standardArray[1];
                dexterity = standardArray[2];
                strength = standardArray[3];
                charisma = standardArray[4];
                intelligence = standardArray[5];
                background = "Hermit";
                race = "Wood Elf";
                height = 5.5;
                AC = 11 + getDexModifier();
                HP = 8 + getConModifier();
                speed = 30;
                age = 550;
                weight = 110;
            }
            case "Warlock" -> {
                charisma = standardArray[0];
                constitution = standardArray[1];
                dexterity = standardArray[2];
                wisdom = standardArray[3];
                intelligence = standardArray[4];
                strength = standardArray[5];
                background = "Charlatan";
                race = "Dark Elf";
                height = 5.5;
                AC = 10 + getDexModifier();
                HP = 10 + getConModifier();
                speed = 30;
                age = 550;
                weight = 110;
            }
            case "Monk" -> {
                dexterity = standardArray[0];
                wisdom = standardArray[1];
                constitution = standardArray[2];
                strength = standardArray[3];
                charisma = standardArray[4];
                intelligence = standardArray[5];
                background = "Hermit";
                race = "Halfling";
                height = 3;
                AC = 10 + getDexModifier() + getWisModifier();
                HP = 8 + getConModifier();
                speed = 25;
                age = 100;
                weight = 40;
            }
            case "Rogue" -> {
                dexterity = standardArray[0];
                charisma = standardArray[1];
                intelligence = standardArray[2];
                constitution = standardArray[3];
                strength = standardArray[4];
                wisdom = standardArray[5];
                background = "Charlatan";
                race = "Gnome";
                height = 3.25;
                AC = 14 + getDexModifier();
                HP = 8 + getConModifier();
                speed = 25;
                age = 300;
                weight = 40;
            }
        }

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


    /*
    //CALCULATE HP
    public int calculateHP(){
        if(level >1){
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
        return HP;
    }
     */

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

    public void generateCharacter() {
        createCharacterID();
        randomName();
        randomClass();
        randomAlignment();
        getLevel();
    }


    public String[] characterArray(){
        return new String[]{
                String.valueOf(characterID),
                characterName,
                characterClass,
                race,
                String.valueOf(level),
                alignment,
                background,
                String.valueOf(AC),
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

    @Override
    public String toString(){
        return "\n------------CHARACTER SUMMARY------------"
                +"\nCharacter ID # "+ characterID
                + ": This character's name is " + characterName
                + ". \nThey are a " + alignment
                + " " + race + " "
                + characterClass
                + ". \nThey come from the " + background
                +" background, Have an AC (armor class) of " + AC
                +", and " + HP
                +" HP (hit points). \nThey are " + age
                + " years old, weigh " + weight
                + " pounds, and they are " + height
                +" feet tall. \nThis level " + level
                + " character has the following main stats: \nStrength = "+strength+" Dexterity = "+dexterity
                + " Constitution = "+constitution + " Intelligence = "+intelligence + " Wisdom = "+wisdom
                + " Charisma = "+charisma
                +"\n-----------------------------------------";
    }





}
