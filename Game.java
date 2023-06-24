import java.util.Random;
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            int rangeStart = 1;
            int rangeEnd = 100;
            int maxAttempts = 10;
            int score = 0;
            boolean playAgain = true;
            System.out.println("Welcome to the Number Guessing Game ");
            while (playAgain) {
                int secretNumber = random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
                int attempts = 0;
                boolean guessedCorrectly = false;
                System.out.println("Find out the number between " + rangeStart + " and " + rangeEnd + ". Can you guess it?");
                while (!guessedCorrectly && attempts < maxAttempts) {
                    System.out.print("Enter your guess Here : ");
                    int guess = scanner.nextInt();
                    attempts++;
                    if (guess == secretNumber) {
                        guessedCorrectly = true;
                        if (attempts == 1) {
                            score += 100;
                        } else if (attempts <= 3) {
                            score += 50;
                        } else if (attempts <= 5) {
                            score += 20;
                        } else if (attempts <= 8) {
                            score += 10;
                        } else {
                            score += 5;
                        }
                        System.out.println(" WOW ! You guessed the number correctly");
                        System.out.println("Score for this round: " + score);
                    } else if (guess < secretNumber) {
                        System.out.println("Naa! The number is higher.");
                    } else {
                        System.out.println("Naa! The number is lower.");
                    }
                }
                if (!guessedCorrectly) {
                    System.out.println("HAHA! You couldn't guess the number, anyways ");
                    System.out.println("The number was: " + secretNumber);
                }
                System.out.print("let's play again? yes/no: ");
                String playAgainResponse = scanner.next();
                playAgain = playAgainResponse.equalsIgnoreCase("yes");
            }
            System.out.println("Total Score: " + score);
        }
    }
}
