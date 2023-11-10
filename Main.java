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
        while (takeAction(getChoice(scanner), playerMap)) {
            System.out.println("");
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

        return playerMap;
    }

    // This function gives action options to the player
    public static byte getChoice(Scanner scanner) {

        // Show options to player
        System.out.println("Options: ");
        System.out.println("1) Check map");
        System.out.println("2) Move");
        System.out.println("3) Investigate area");
        System.out.println("4) Check inventory");
        System.out.println("5) Use main weapon");
        System.out.println("6) Use secondary weapon");
        System.out.println("7) Quit game");
        byte choice = 0;

        // Make sure the input is a number and it's within the provided range
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(" < ");
                choice = scanner.nextByte();
                if (0 < choice && choice < 8) {
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

        return choice;
    }

    // This function enacts the action chosen by the player
    public static boolean takeAction(byte choice, Map <String, Object> player) {
        switch (choice) {
            case 1:
                // Check map
                checkMap(player);
                return true;
            case 2:
                // Move
                move(player);
                return true;
            case 3:
                // Investigate area
                investigate(player);
                return true;
            case 4:
                // Check Inventory
                checkInventory(player);
                return true;
            case 5:
                // Use main weapon
                useMainWeapon(player);
                return true;
            case 6:
                // Use secondary weapon
                useSecondaryWeapon(player);
                return true;
            case 7:
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
        System.out.println("You check your map");
    }

    // This function lets the player move
    public static void move(Map <String, Object> player) {
        System.out.println("You move");
    }

    // This function tests if the player notices anything from their location
    public static void investigate(Map <String, Object> player) {
        System.out.println("You investigate the area");
    }

    // This function shows the player their inventory
    public static void checkInventory(Map <String, Object> player) {
        System.out.println("You check your inventory");
    }

    // This function uses the player's main weapon
    public static void useMainWeapon(Map <String, Object> player) {
        System.out.println("You use the main weapon");
    }

    // This function uses the player's secondary weapon
    public static void useSecondaryWeapon(Map <String, Object> player) {
        System.out.println("You use the secondary weapon");
    }



}