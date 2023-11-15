package avlyakulov.timur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private final File file = new File("src/main/java/avlyakulov/timur/words.txt");
    List<String> words = new ArrayList<>();

    public String getWordToStartGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String wordInLine;
            while ((wordInLine = reader.readLine()) != null) {
                words.add(wordInLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chooseRandomWordFromList(words);
    }

    public String chooseRandomWordFromList(List<String> words) {
        Random random = new Random();
        return words.get(random.nextInt(0, words.size()));
    }
}
