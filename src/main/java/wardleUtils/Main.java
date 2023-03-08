package wardleUtils;
// Import Base Libraries
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.lang.String;
// Functions
public class Main {
    public static ArrayList<Character> correctChar = new ArrayList<Character>();
    public static ArrayList<Character> misplacedChar = new ArrayList<Character>();
    public static ArrayList<Character> wrongChar = new ArrayList<Character>();
    // Build Arraylist of words
    public static ArrayList<String> wordList() throws Exception {
        ArrayList<String> wordles = new ArrayList<>();
        String word;
        InputStream inputStream = Main.class.getResourceAsStream("/words.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        // Parse File
        while ((word = reader.readLine()) != null) {
            wordles.add(word);
        }
        return wordles;
    }
    // Pick random word from the list
    public static String wordlGet() throws Exception {
        // Setup
        Random rand = new Random();
        String output;
        // Output
        output = wordList().get(rand.nextInt(wordList().size()));
        while (output == "null") {
            output = wordList().get(rand.nextInt(wordList().size()));
        }
        // Return
        return output;
    }
    // Compare input to chosen word
    public static String wordCompare(String input, String wordl) {
        StringBuilder outputChecker = new StringBuilder(input);
        StringBuilder output = new StringBuilder();
        if (input.length() == 5) {
            for (int i = 0; i < 5; i++) {
                if (input.charAt(i) == outputChecker.charAt(i)) {
                    correctChar.add(input.charAt(i));
                    outputChecker.replace(i, (i + 1), " ");
                } else if (outputChecker.toString().contains(String.valueOf(input.charAt(i)))) {
                    misplacedChar.add(input.charAt(i));
                    outputChecker.replace((outputChecker.indexOf(String.valueOf(input.charAt(i)))),
                            (outputChecker.indexOf(String.valueOf(input.charAt(i))) + 1), " ");
                } else {
                    wrongChar.add(input.charAt(i));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            output.append(input.charAt(i));
            if (i > 3) {
            } else
                output.append(" ");
        }
        return output.toString();

    }
}