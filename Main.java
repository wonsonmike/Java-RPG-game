import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main (String[] args) {
        // Welcome player, and prepare scanner for any input
        System.out.println("Welcome to NoSnow RPG.\n");
        Scanner scanner = new Scanner(System.in);

        // Set up the player attributes
        Map<String, Object> playerMap = setPlayer(getClassType(scanner));
        System.out.println("Your class is " + playerMap.get("Class"));

        // Let the user play the game
        System.out.println("\nYou find yourself in the forest. What do you do?");
        checkMap(playerMap);
        while (takeAction(getChoice(scanner), playerMap, scanner)) {
            System.out.println("\n");
            checkMap(playerMap);
        }
        
        // Close the scanner and the program is over
        scanner.close();
    }

    // This function sets the class type for the user
    public static byte getClassType(Scanner scanner) {
        // Get the class type from the user
        System.out.println("Choose your class: ");
        System.out.println("1) Archer");
        System.out.println("2) Barbarian");
        System.out.println("3) Rogue");
        
        byte classType = 0;
        boolean validInput = false;

        // Make sure the input is a number and it's within the provided range
        while (!validInput) {
            try {
                System.out.print(" < ");
                classType = scanner.nextByte();
                if (0 < classType && classType < 4) {
                    validInput = true;}
                else {
                    System.out.println("Invalid input. Please enter a number within the given range.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the number of the class you'd like.");
                scanner.nextLine();
            }
        }

        return classType;
    }

    // This function sets the beginning player stats dependant on the class chosen
    public static Map <String, Object> setPlayer(byte type) {

        Map<String, Object> playerMap = new HashMap<>();

        switch (type) {
            case 1:
                playerMap.put("Class", "Archer");
                playerMap.put("Str", 10);
                playerMap.put("Dex", 17);
                playerMap.put("Con", 14);
                playerMap.put("Int", 11);
                playerMap.put("Wis", 14);
                playerMap.put("Cha", 10);
                playerMap.put("AC", 13);
                playerMap.put("HP", 12);
                playerMap.put("Weapon1name", "Short bow");
                playerMap.put("Weapon1damage", 10);
                playerMap.put("Weapon2name", "Dagger");
                playerMap.put("Weapon2damage", 4);
                break;
            case 2:
                playerMap.put("Class", "Barbarian");
                playerMap.put("Str", 16);
                playerMap.put("Dex", 14);
                playerMap.put("Con", 16);
                playerMap.put("Int", 8);
                playerMap.put("Wis", 10);
                playerMap.put("Cha", 12);
                playerMap.put("AC", 15);
                playerMap.put("HP", 15);
                playerMap.put("Weapon1name", "Great Club");
                playerMap.put("Weapon1damage", 10);
                playerMap.put("Weapon2name", "Short sword");
                playerMap.put("Weapon2damage", 6);
                break;
            case 3:
                playerMap.put("Class", "Rogue");
                playerMap.put("Str", 10);
                playerMap.put("Dex", 17);
                playerMap.put("Con", 12);
                playerMap.put("Int", 11);
                playerMap.put("Wis", 14);
                playerMap.put("Cha", 15);
                playerMap.put("AC", 14);
                playerMap.put("HP", 9);
                playerMap.put("Weapon1name", "Dagger");
                playerMap.put("Weapon1damage", 6);
                playerMap.put("Weapon2name", "Throwing stars");
                playerMap.put("Weapon2damage", 4);
                break;
            default:
                playerMap.put("Class", "Barbarian");
                playerMap.put("Str", 16);
                playerMap.put("Dex", 14);
                playerMap.put("Con", 16);
                playerMap.put("Int", 8);
                playerMap.put("Wis", 10);
                playerMap.put("Cha", 12);
                playerMap.put("AC", 15);
                playerMap.put("HP", 15);
                playerMap.put("Weapon1name", "Great Club");
                playerMap.put("Weapon1damage", 10);
                playerMap.put("Weapon2name", "Short sword");
                playerMap.put("Weapon2damage", 6);

        }

        playerMap.put("Location", 0);
        return playerMap;
    }

    // This function gives action options to the player
    public static byte getChoice(Scanner scanner) {

        // Show options to player
        System.out.println("Options: ");
        System.out.println("1) Move");
        System.out.println("2) Investigate area");
        System.out.println("3) Use main weapon");
        System.out.println("4) Use secondary weapon");
        System.out.println("5) Quit game");
        byte choice = 0;

        // Make sure the input is a number and it's within the provided range
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(" < ");
                choice = scanner.nextByte();
                if (0 < choice && choice < 6) {
                    validInput = true;}
                else {
                    System.out.println("Invalid input. Please enter a number within the given range.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the number of the class you'd like.");
                scanner.nextLine();
            }
        }
        System.out.println("\n");
        return choice;
    }

    // This function enacts the action chosen by the player
    public static boolean takeAction(byte choice, Map <String, Object> player, Scanner scanner) {
        switch (choice) {
            case 1:
                // Move
                move(player, scanner);
                return true;
            case 2:
                // Investigate area
                investigate(player);
                return true;
            case 3:
                // Use main weapon
                useMainWeapon(player);
                return true;
            case 4:
                // Use secondary weapon
                useSecondaryWeapon(player);
                return true;
            case 5:
                // Quit game
                System.out.println("You quit the game");
                return false;
            default:
                System.out.println("You did nothing.");
                return true;
        }
    }

    // This function shows the map to the player
    public static void checkMap(Map <String, Object> player) {
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < 44; i++) { System.out.print("-");}
            System.out.print("\n");
            for (int i = 0; i < 4; i++) {
                System.out.print((x * 5) + i);
                if (x == 2 && i == 1) {System.out.print(" Cliff  |");}
                else {System.out.print(" Forest |");} }
            System.out.print((x * 5) + 4);
            System.out.print("Forest\n");
            for (int i = 0; i < 5; i++) { 
                if (i != 0) { System.out.print("|");}
                if ((x * 5) + i == (int)player.get("Location")) {
                    // The player is here
                    System.out.print("  You   ");
                }
                else {
                    System.out.print("        ");
                }
            }
            System.out.print("\n");
        }
        for (int i = 0; i < 44; i++) { System.out.print("-");}
        System.out.print("\n");
    }

    // This function lets the player move
    public static void move(Map <String, Object> player, Scanner scanner) {
        System.out.println("Where do you want to move?");
        System.out.println("1) North");
        System.out.println("2) East");
        System.out.println("3) South");
        System.out.println("4) West");
        byte choice = 0;
        int location = (int)player.get("Location");

        // Make sure the input is a number and it's within the provided range
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(" < ");
                choice = scanner.nextByte();
                if (0 > choice || choice > 5) {
                    System.out.println("Invalid input. Please enter a number within the given range.");
                }
                else if (choice == 1 && location < 5) {
                    System.out.println("You can't move any further north. Please enter another direction.");
                }
                else if (choice == 2 && (location % 5) == 4) {
                    System.out.println("You can't move any further east. Please enter another direction.");
                }
                else if (choice == 3 && location > 14) {
                    System.out.println("You can't move any further south. Please enter another direction.");
                }
                else if (choice == 4 && (location % 5) == 0) {
                    System.out.println("You can't move any further west. Please enter another direction.");
                }
                else {
                    validInput = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the number of the class you'd like.");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                location -= 5;
                System.out.println("You moved to the north.");
                break;
            case 2:
                location += 1;
                System.out.println("You moved to the east.");
                break;
            case 3:
                location += 5;
                System.out.println("You moved to south.");
                break;
            case 4:
                location -= 1;
                System.out.println("You moved to west.");
                break;
            default:
        }
        
        player.put("Location", location);
        passiveCheck(player);
    }

    // This function tests if the player notices anything from their location
    public static void investigate(Map <String, Object> player) {
        System.out.println("You investigate the area");
        int location = (int)player.get("Location");
        
        if (location == 11) {
            System.out.println("You see a goblin camp over the cliff, to the east.");
        }
        else if (location == 6) {
            System.out.println("You hear some creatures to the south east.");
        }
        else if (location == 16) {
            System.out.println("You hear some creatures to the north east.");
        }
        else if (location == 7 || location == 8 || location == 13 || location == 17 || location == 18) {
            System.out.println("You hear goblins to the east of the cliff.");
        }
        else if (location == 9 || location == 14 || location == 19 ) {
            System.out.println("You hear some creatures to the west.");
        }
        else if (location == 12) {
            System.out.println("You are in a goblin camp. There are two goblins.");
        }
        else if (location == 4) {
            System.out.println("You find a chest.");
        }
        else {
            System.out.println("You are in the forest");
        }
        
    }

    public static void passiveCheck(Map <String, Object> player) {
        int location = (int)player.get("Location");
        
        if (location == 11) {
            System.out.println("You are on a cliff.");
        }
        else if (location == 6 || location == 16) {
            System.out.println("You hear something nearby.");
        }
        else if (location == 7 || location == 8 || location == 13 || location == 17 || location == 18) {
            System.out.println("You hear goblins near you.");
        }
        else if (location == 9 || location == 14 || location == 19 ) {
            System.out.println("You hear something to the west.");
        }
        else if (location == 12) {
            System.out.println("You are in a goblin camp.");
        }
        else if (location == 4) {
            System.out.println("You find a chest.");
        }
        else {
            System.out.println("You are in the forest");
        }
        
    }

    // This function uses the player's main weapon
    public static void useMainWeapon(Map <String, Object> player) {
        if ((byte)player.get("Location") == 12) {
            System.out.println("You use the main weapon"); }
        else if ((byte)player.get("Location") == 11 && player.get("Class") == "Archer") {
            System.out.println("You use the main weapon.");
        }
        else {
            System.out.println("You have nothing to use the main weapon on.");
        }
    }

    // This function uses the player's secondary weapon
    public static void useSecondaryWeapon(Map <String, Object> player) {
        if ((byte)player.get("Location") == 12) {
            System.out.println("You use the secondary weapon"); }
        else if ((byte)player.get("Location") == 11 && player.get("Class") == "Rogue") {
            System.out.println("You use the secondary weapon.");
        }
        System.out.println("You have nothing to use the secondary weapon on.");
    }



}