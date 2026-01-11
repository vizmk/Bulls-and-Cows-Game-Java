import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Calculate calculate=new Calculate();

        Scanner scanner=new Scanner(System.in);
        //guess code
        String guess= scanner.next();
        calculate.validator(guess);

    }
}
