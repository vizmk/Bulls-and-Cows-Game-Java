import java.util.Scanner;

public class Main {

    private static boolean isSingleInteger(String s) {
        // must be exactly one integer token (no spaces, no extra junk)
        return s.matches("-?\\d+");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String lenLine = scanner.nextLine().trim();

        if (!isSingleInteger(lenLine)) {
            System.out.println("Error: \"" + lenLine + "\" isn't a valid number.");
            return;
        }

        int secretLength = Integer.parseInt(lenLine);
        if (secretLength <= 0) {
            System.out.println("Error: \"" + lenLine + "\" isn't a valid number.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String symLine = scanner.nextLine().trim();

        if (!isSingleInteger(symLine)) {
            System.out.println("Error: \"" + symLine + "\" isn't a valid number.");
            return;
        }

        int possibleSymbols = Integer.parseInt(symLine);
        if (possibleSymbols <= 0) {
            System.out.println("Error: \"" + symLine + "\" isn't a valid number.");
            return;
        }

        if (possibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        if (secretLength > possibleSymbols) {
            System.out.println("Error: it's not possible to generate a code with a length of "
                    + secretLength + " with " + possibleSymbols + " unique symbols.");
            return;
        }

        Calculate game = new Calculate();
        game.generateSecret(secretLength, possibleSymbols);

        System.out.println(game.getPreparedLine());
        System.out.println("Okay, let's start a game!");

        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.nextLine().trim();

            // Handle wrong guess length
            if (guess.length() != secretLength) {
                System.out.println("Error: the length of the guess must be " + secretLength + ".");
                return;
            }

            // Handle invalid symbols in guess
            if (!game.isGuessValid(guess)) {
                System.out.println("Error: the guess contains invalid symbols.");
                return;
            }

            System.out.println(game.evaluateGuess(guess));

            if (game.getBulls() == secretLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            turn++;
        }
    }
}
