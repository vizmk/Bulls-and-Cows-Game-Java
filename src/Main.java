import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculate calculate = new Calculate();

        System.out.println("Please, enter the secret code's length:");
        int secretLength = scanner.nextInt();

        if (secretLength <= 0) {
            System.out.println("Error: the length must be a positive number.");
            return;
        }

        if (secretLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of "
                    + secretLength
                    + " because there aren't enough unique digits.");
            return;
        }

        calculate.generateSecret(secretLength);

        System.out.println("Okay, let's start a game!");

        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.next();

            calculate.evaluateGuess(guess);

            if (calculate.getBulls() == secretLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            turn++;
        }
    }
}
