import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
            // If the goblins are all dead, end the game
            if ((int)playerMap.get("GoblinHP") <= 0) {
                System.out.println("You killed all goblins. You won!");
                break;
            }
            checkMap(playerMap);
            // If you're in the goblin camp, the goblins attack
            if ((int)playerMap.get("Location") == 12) {
                Random random = new Random();
                int damage = random.nextInt(3) + 1;
                int hp = (int)playerMap.get("HP");
                hp -= damage;
                if (hp <= 0) {
                    System.out.println("You are in a goblin camp. You are attacked by the goblins, and died. Game over.");
                    break;
                }
                System.out.println("You are in a goblin camp. You are attacked by the goblins and take " + damage + " damage. You now have " + hp + " HP.");
                playerMap.put("HP", hp);
            }
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

        // Set the attributes for the player
        switch (type) {
            case 1:
                playerMap.put("Class", "Archer");
                playerMap.put("HP", 12);
                playerMap.put("Weapon1name", "Short bow");
                playerMap.put("Weapon1damage", 10);
                playerMap.put("Weapon2name", "Dagger");
                playerMap.put("Weapon2damage", 6);
                break;
            case 2:
                playerMap.put("Class", "Barbarian");
                playerMap.put("HP", 15);
                playerMap.put("Weapon1name", "Great Club");
                playerMap.put("Weapon1damage", 10);
                playerMap.put("Weapon2name", "Short sword");
                playerMap.put("Weapon2damage", 6);
                break;
            case 3:
                playerMap.put("Class", "Rogue");
                playerMap.put("HP", 9);
                playerMap.put("Weapon1name", "Dagger");
                playerMap.put("Weapon1damage", 6);
                playerMap.put("Weapon2name", "Throwing stars");
                playerMap.put("Weapon2damage", 4);
                break;
            default:
                playerMap.put("Class", "Barbarian");
                playerMap.put("HP", 15);
                playerMap.put("Weapon1name", "Great Club");
                playerMap.put("Weapon1damage", 10);
                playerMap.put("Weapon2name", "Short sword");
                playerMap.put("Weapon2damage", 6);

        }

        playerMap.put("Location", 0);
        playerMap.put("GoblinHP", 12);
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
                return move(player, scanner);
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
                if (x == 2 && i == 1) {System.out.print(" Cliff  |");}
                else {System.out.print(" Forest |");} 
            }
            System.out.print(" Forest\n");
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

    // This function lets the player move. Returns false if the player's movement kills them.
    public static boolean move(Map <String, Object> player, Scanner scanner) {
        // Gives movement options to the player
        System.out.println("Where do you want to move?");
        System.out.println("1) North");
        System.out.println("2) East");
        System.out.println("3) South");
        System.out.println("4) West");
        byte choice = 0;
        int location = (int)player.get("Location");

        // Make sure the input is a number and it's a valid input
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(" < ");
                choice = scanner.nextByte();
                if (0 > choice || choice > 4) {
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
                else if (choice == 4 && location == 12) {
                    System.out.println("You can't move to the west, there's a cliff there.");
                }
                else {
                    validInput = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the number of the direction you want to go.");
                scanner.nextLine();
            }
        }

        // Verifies movement to the user
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
                System.out.println("You moved to the south.");
                break;
            case 4:
                location -= 1;
                System.out.println("You moved to the west.");
                break;
            default:
        }

        return movementCheck(player, location, scanner);
    }

    // This function checks if the user's movement warrants anything to happen
    public static boolean movementCheck(Map <String, Object> player, int location, Scanner scanner) {
        int oldLocation = (int)player.get("Location");
        
        // If they fall off the cliff, deal damage.
        if (oldLocation == 11 && location == 12) {
            Random random = new Random();
            int damage = random.nextInt(8) + 1;
            int hp = (int)player.get("HP");
            hp -= damage;
            if (hp <= 0) {
                System.out.println("You fell off the cliff. You died. Game over.");
                return false;
            }
            System.out.println("You fell off the cliff. You took " + damage + " damage. You now have " + hp + " HP.");
            player.put("HP", hp);
        }

        // If they entered the goblin camp smartly, let them attack first.
        if ((oldLocation == 7 || oldLocation == 13 || oldLocation == 17) && location == 12) {
            System.out.println("You are entering the goblin camp. Would you like to attack or leave?");
            System.out.println("1) Attack");
            System.out.println("2) Leave");
            byte choice = 0;
            // Make sure the input is a number and it's a valid input
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print(" < ");
                    choice = scanner.nextByte();
                    if (0 > choice || choice > 2) {
                        System.out.println("Invalid input. Please enter a number within the given range.");
                    }
                    else {
                        validInput = true;
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter the number of the direction you want to go.");
                    scanner.nextLine();
                }
            }

            // Enact the player's choice
            if (choice == 1) {
                // Player attacks
                System.out.println("Which weapon do you want to use?");
                System.out.println("1) " + player.get("Weapon1name"));
                System.out.println("2) " + player.get("Weapon2name"));
                byte weapon = 0;
                // Make sure the input is a number and it's a valid input
                boolean validChoice = false;
                while (!validChoice) {
                    try {
                        System.out.print(" < ");
                        weapon = scanner.nextByte();
                        if (0 > weapon || weapon > 2) {
                            System.out.println("Invalid input. Please enter a number within the given range.");
                        }
                        else {
                            validChoice = true;
                        }
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter the number of the weapon you want to use.");
                        scanner.nextLine();
                    }
                }

                // They've made their choice, set their location and use the weapon
                player.put("Location", location);
                if (weapon == 1) {
                    useMainWeapon(player);
                }
                else {
                    useSecondaryWeapon(player);
                }
                return true;
            }
            else {
                // Player leaves
                System.out.println("You go back the way you came. The goblins don't notice you.");
                return true;
            }

        }


        player.put("Location", location);
        passiveCheck(player);
        return true;
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

    // This function tests if the player notices anything from their new location
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
        if ((int)player.get("Location") == 12 && player.get("Class") != "Archer") {
            System.out.println("You use your " + player.get("Weapon1name"));
            // Deal damage in the range that the weapon allows 
            Random random = new Random();
            int damage = random.nextInt((int)player.get("Weapon1damage")) + 1;
            int hp = (int)player.get("GoblinHP");
            hp -= damage;
            System.out.println("You dealt " + damage + " damage to the goblins.");
            player.put("GoblinHP", hp);
        }
        else if ((int)player.get("Location") == 12 && player.get("Class") == "Archer") {
            System.out.println("You are too close to use your Short bow. You stab a goblin with an arrow."); 
            // deal 2 damage
            player.put("GoblinHP", ((int)player.get("GoblinHP") - 2)); 
        }
        else if (((int)player.get("Location") == 11 || (int)player.get("Location") == 13 || (int)player.get("Location") == 17 || (int)player.get("Location") == 7) && player.get("Class") == "Archer") {
            System.out.println("You use your Short bow.");
            // Deal damage in the range that the weapon allows 
            Random random = new Random();
            int damage = random.nextInt((int)player.get("Weapon1damage")) + 1;
            int hp = (int)player.get("GoblinHP");
            hp -= damage;
            System.out.println("You dealt " + damage + " damage to the goblins.");
            player.put("GoblinHP", hp);
        }
        else {
            System.out.println("You have nothing to use the main weapon on.");
        }
    }

    // This function uses the player's secondary weapon
    public static void useSecondaryWeapon(Map <String, Object> player) {
        if ((int)player.get("Location") == 12) {
            System.out.println("You use your " + player.get("Weapon2name"));
            // Deal damage in the range that the weapon allows 
            Random random = new Random();
            int damage = random.nextInt((int)player.get("Weapon2damage")) + 1;
            int hp = (int)player.get("GoblinHP");
            hp -= damage;
            System.out.println("You dealt " + damage + " damage to the goblins.");
            player.put("GoblinHP", hp);
        }
        else if (((int)player.get("Location") == 6 || (int)player.get("Location") == 7 || (int)player.get("Location") == 8 || (int)player.get("Location") == 11 || (int)player.get("Location") == 13 || (int)player.get("Location") == 16 || (int)player.get("Location") == 17 || (int)player.get("Location") == 18) && player.get("Class") == "Rogue") {
            System.out.println("You use your " + player.get("Weapon2name"));
            // Deal damage in the range that the weapon allows 
            Random random = new Random();
            int damage = random.nextInt((int)player.get("Weapon2damage")) + 1;
            int hp = (int)player.get("GoblinHP");
            hp -= damage;
            System.out.println("You dealt " + damage + " damage to the goblins.");
            player.put("GoblinHP", hp);
        }
        else {
            System.out.println("You have nothing to use the secondary weapon on.");
        }
    }

}