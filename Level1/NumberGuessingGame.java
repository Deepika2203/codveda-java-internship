import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int numberToGuess = random.nextInt(100) + 1; // 1 to 100
        int attempts = 5;
        int guess;

        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100.");
        System.out.println("You have " + attempts + " attempts.");

        for (int i = 1; i <= attempts; i++) {

            System.out.print("Attempt " + i + " - Enter your guess: ");
            guess = sc.nextInt();

            if (guess == numberToGuess) {
                System.out.println("🎉 Congratulations! You guessed the correct number.");
                sc.close();
                return;
            } else if (guess > numberToGuess) {
                System.out.println("Too high!");
            } else {
                System.out.println("Too low!");
            }
        }

        System.out.println("❌ You've used all attempts.");
        System.out.println("The correct number was: " + numberToGuess);
        sc.close();
    }
}
