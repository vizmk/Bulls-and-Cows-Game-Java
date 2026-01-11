import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Calculate calculate=new Calculate();
        /*



        //guess code
        String guess= scanner.next();
        calculate.validator(guess);
        */
        int lenght = scanner.nextInt();
        if (lenght > 10) {
            System.out.println(
                    "Error: can't generate a secret number with a length of "
                            + lenght
                            + " because there aren't enough unique digits."
            );
            return;
        }
        String secret = calculate.randomSecret(lenght);

        System.out.println("The random secret number is " + secret + ".");

    }
}

