public class Calculate {

    private String secret = "9305";
    private int bulls = 0;
    private int cows = 0;

    public void validator(String guess) {

        // calcolo bulls e cows
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                bulls++;
            } else if (secret.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }

        // stampe (UNA SOLA VIENE ESEGUITA)
        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None. The secret code is 9305.");
        } else if (bulls > 0 && cows > 0) {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s). The secret code is 9305.");
        } else if (bulls > 0) {
            System.out.println("Grade: " + bulls + " bull(s). The secret code is 9305.");
        } else {
            System.out.println("Grade: " + cows + " cow(s). The secret code is 9305.");
        }
    }
}
