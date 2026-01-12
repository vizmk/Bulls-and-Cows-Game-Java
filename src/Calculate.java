public class Calculate {

    private String secret;
    private int bulls;
    private int cows;

    public void generateSecret(int length) {
        secret = randomSecret(length);
    }

    public void evaluateGuess(String guess) {
        bulls = 0;
        cows = 0;

        for (int i = 0; i < secret.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                bulls++;
            } else if (secret.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }

        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None");
        } else if (bulls > 0 && cows == 0) {
            System.out.println(bulls == 1 ? "Grade: 1 bull" : "Grade: " + bulls + " bulls");
        } else if (bulls == 0 && cows > 0) {
            System.out.println(cows == 1 ? "Grade: 1 cow" : "Grade: " + cows + " cows");
        } else {
            System.out.println("Grade: " + bulls + " bulls and " + cows + " cows");
        }
    }

    public int getBulls() {
        return bulls;
    }

    private String randomSecret(int length) {
        boolean[] used = new boolean[10];
        StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            int digit = (int) (Math.random() * 10);

            if (sb.length() == 0 && digit == 0) continue; // no leading zero
            if (used[digit]) continue;

            used[digit] = true;
            sb.append(digit);
        }

        return sb.toString();
    }
}
