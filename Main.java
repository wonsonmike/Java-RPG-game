import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        System.out.println("Welcome to NoSnow RPG.");
        System.out.println("Choose your class: ");
        System.out.println("1) Archer");
        System.out.println("2) Barbarian");
        System.out.println("3) Rogue");
        Scanner scanner = new Scanner(System.in);
        byte classType = 0;
        boolean validInput = false;

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

        System.out.println("Your class is type " + classType);
        scanner.close();
    }
}