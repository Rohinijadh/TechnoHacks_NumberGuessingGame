import java.util.Random;
import javax.swing.JOptionPane;

public class RandomNumberGuessingGame {

    public static void main(String[] args) {
        boolean playAgain;
        int totalScore = 0; // Variable to keep track of the total score across rounds

        do {
            String inputMin = JOptionPane.showInputDialog("Enter the minimum number:");
            String inputMax = JOptionPane.showInputDialog("Enter the maximum number:");

            int min = Integer.parseInt(inputMin);
            int max = Integer.parseInt(inputMax);

            if (min >= max) {
                JOptionPane.showMessageDialog(null, "Invalid range. Minimum number must be less than the maximum number.");
            } else {
                int score = playGame(min, max);
                totalScore += score;
                JOptionPane.showMessageDialog(null, "Your score in this round: " + score +
                                                      "\nTotal score: " + totalScore);
            }

            // Ask the user if they want to play another round
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play another round?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (choice == JOptionPane.YES_OPTION);
        } while (playAgain);
    }

    private static int playGame(int min, int max) {
        int randomNumber = generateRandomNumber(min, max);
        int maxAttempts = 5; // You can change this value to set the maximum number of attempts.
        int userGuess;
        String inputGuess;
        String message;
        int score = 0;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            inputGuess = JOptionPane.showInputDialog("Attempt " + attempt + " of " + maxAttempts +
                                                      "\nGuess the random number:");
            userGuess = Integer.parseInt(inputGuess);

            if (userGuess == randomNumber) {
                int attemptScore = calculateAttemptScore(attempt);
                score += attemptScore;
                message = "Congratulations! Your guess is correct. You scored " + attemptScore + " points.";
                JOptionPane.showMessageDialog(null, message);
                break;
            } else if (userGuess < randomNumber) {
                message = "Try again. The random number is higher than your guess.";
            } else {
                message = "Try again. The random number is lower than your guess.";
            }

            JOptionPane.showMessageDialog(null, message);

            if (attempt == maxAttempts) {
                JOptionPane.showMessageDialog(null, "Sorry, you've used all your attempts. The correct number was: " + randomNumber);
            }
        }

        return score;
    }

    private static int calculateAttemptScore(int attempt) {
        // Assign scores based on the attempt number
        if (attempt == 1) {
            return 10;
        } else if (attempt == 2) {
            return 7;
        } else if (attempt == 3) {
            return 5;
        } else if (attempt == 4) {
            return 3;
        } else {
            return 1;
        }
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        // The formula below generates a random number between min (inclusive) and max (inclusive).
        return random.nextInt(max - min + 1) + min;
    }
}
