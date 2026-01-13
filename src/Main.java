import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculate game = new Calculate();

        System.out.println("Input the length of the secret code:");
        String lenStr = scanner.nextLine().trim();
        int secretLength;
        try {
            secretLength = Integer.parseInt(lenStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + lenStr + " isn't a valid number.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String symStr = scanner.nextLine().trim();
        int possibleSymbols;
        try {
            possibleSymbols = Integer.parseInt(symStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + symStr + " isn't a valid number.");
            return;
        }

        // validations
        if (secretLength <= 0) {
            System.out.println("Error: " + secretLength + " isn't a valid number.");
            return;
        }

        if (possibleSymbols <= 0) {
            System.out.println("Error: " + possibleSymbols + " isn't a valid number.");
            return;
        }

        if (possibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        if (secretLength > possibleSymbols) {
            System.out.println("Error: can't generate a secret number with a length of "
                    + secretLength + " because there aren't enough unique symbols.");
            return;
        }

        // generate + print prepared line
        game.generateSecret(secretLength, possibleSymbols);
        System.out.println(game.getPreparedLine());

        System.out.println("Okay, let's start a game!");

        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.nextLine().trim();

            String grade = game.evaluateGuess(guess);
            System.out.println(grade);

            if (game.getBulls() == secretLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            turn++;
        }
    }
}
