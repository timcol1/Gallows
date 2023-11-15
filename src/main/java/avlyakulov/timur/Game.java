package avlyakulov.timur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    Player player;

    Dictionary dictionary;
    GameState gameState;

    String word;

    public Game() {
        dictionary = new Dictionary();
        player = new Player();
        gameState = new GameState();
        word = dictionary.getWordToStartGame();
    }

    public void startGame() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Do you want to start a game\n1-Yes\n0-No");
            String answer = reader.readLine();
            checkUserAnswerToStartGame(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkUserAnswerToStartGame(String answer) {
        switch (answer) {
            case "0" -> System.exit(0);
            case "1" -> guessingTheWord(word);
        }
    }

    public void guessingTheWord(String word) {
        System.out.println("The game has begun");
        char[] wordInChars = word.toCharArray();
        char[] wordInCharsEncoded = new char[wordInChars.length];
    }
}
