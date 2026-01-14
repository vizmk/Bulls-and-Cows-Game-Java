import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    private String secret = "";
    private int bulls = 0;
    private int cows = 0;

    private int possibleSymbols = 0;
    private String pool = "";

    public void generateSecret(int length, int possibleSymbols) {
        this.possibleSymbols = possibleSymbols;
        this.pool = ALPHABET.substring(0, possibleSymbols);

        // Shuffle pool chars and take first "length" -> unique by construction
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < pool.length(); i++) {
            chars.add(pool.charAt(i));
        }
        Collections.shuffle(chars);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.get(i));
        }
        secret = sb.toString();
    }

    public String getPreparedLine() {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < secret.length(); i++) stars.append('*');

        char last = pool.charAt(pool.length() - 1);

        String range;
        if (possibleSymbols <= 10) {
            range = "(0-" + last + ")";
        } else {
            range = "(0-9, a-" + last + ")";
        }

        return "The secret is prepared: " + stars + " " + range + ".";
    }

    public boolean isGuessValid(String guess) {
        // Every char in guess must be inside the allowed pool
        for (int i = 0; i < guess.length(); i++) {
            if (pool.indexOf(guess.charAt(i)) < 0) return false;
        }
        return true;
    }

    public String evaluateGuess(String guess) {
        bulls = 0;
        cows = 0;

        for (int i = 0; i < secret.length(); i++) {
            char g = guess.charAt(i);
            char s = secret.charAt(i);

            if (g == s) {
                bulls++;
            } else if (secret.indexOf(g) >= 0) {
                cows++;
            }
        }

        return formatGrade(bulls, cows);
    }

    private String formatGrade(int bulls, int cows) {
        if (bulls == 0 && cows == 0) return "Grade: None";

        StringBuilder sb = new StringBuilder("Grade: ");

        if (bulls > 0) {
            sb.append(bulls).append(bulls == 1 ? " bull" : " bulls");
        }

        if (cows > 0) {
            if (bulls > 0) sb.append(" and ");
            sb.append(cows).append(cows == 1 ? " cow" : " cows");
        }

        return sb.toString();
    }

    public int getBulls() {
        return bulls;
    }
}
