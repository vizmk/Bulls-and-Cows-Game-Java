import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    private String secret = "";
    private int bulls = 0;
    private int cows = 0;

    private int possibleSymbols = 0;

    public void generateSecret(int length, int possibleSymbols) {
        this.possibleSymbols = possibleSymbols;

        String pool = ALPHABET.substring(0, possibleSymbols);

        // Shuffle pool characters, then take first 'length' => guaranteed unique
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
        for (int i = 0; i < secret.length(); i++) {
            stars.append('*');
        }

        String pool = ALPHABET.substring(0, possibleSymbols);
        char last = pool.charAt(pool.length() - 1);

        String range;
        if (possibleSymbols <= 10) {
            // 0-<last digit>
            range = "(0-" + last + ")";
        } else {
            // 0-9, a-<last letter>
            range = "(0-9, a-" + last + ")";
        }

        return "The secret is prepared: " + stars + " " + range + ".";
    }

    public String evaluateGuess(String guess) {
        bulls = 0;
        cows = 0;

        int n = Math.min(guess.length(), secret.length());

        for (int i = 0; i < n; i++) {
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
        if (bulls == 0 && cows == 0) {
            return "Grade: None";
        }

        StringBuilder sb = new StringBuilder("Grade: ");

        boolean wrote = false;

        if (bulls > 0) {
            sb.append(bulls).append(bulls == 1 ? " bull" : " bulls");
            wrote = true;
        }

        if (cows > 0) {
            if (wrote) sb.append(" and ");
            sb.append(cows).append(cows == 1 ? " cow" : " cows");
        }

        return sb.toString();
    }

    public int getBulls() {
        return bulls;
    }
}
