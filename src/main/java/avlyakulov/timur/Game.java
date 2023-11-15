package avlyakulov.timur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    Player player;

    Dictionary dictionary;
    GameState gameState;

    String word;

    List<Character> enteredLetters = new ArrayList<>();


    public void startGame() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Do you want to start a game\n1-Yes\n0-No");
            String answer = reader.readLine();
            checkUserAnswerToStartGame(answer, reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkUserAnswerToStartGame(String answer, BufferedReader reader) {
        switch (answer) {
            case "0" -> System.exit(0);
            case "1" -> {
                dictionary = new Dictionary();
                player = new Player();
                gameState = new GameState();
                word = dictionary.getWordToStartGame().toLowerCase();
                enteredLetters.clear();
                guessingTheWord(reader);
            }
            default -> System.out.println("You enter the wrong answer");
        }
    }

    public void guessingTheWord(BufferedReader reader) {
        System.out.println("The game has begun");
        char[] wordInChars = word.toCharArray();
        char[] encodedCharWord = createEncodedCharWord(wordInChars.length);
        char letter = ' ';
        gameState.printGameState(player.getLife(), encodedCharWord, enteredLetters);
        while (player.getLife() > 0) {
            letter = enterTheLetter(reader);
            encodedCharWord = checkLetterIntTheWord(letter, encodedCharWord, wordInChars);
            gameState.printGameState(player.getLife(), encodedCharWord, enteredLetters);
            checkWinCondition(encodedCharWord);
        }
        System.out.println("\u001B[31mYou lost\u001B[0m");
        System.out.println("\033[34mThe hidden word is " + word + "\033[0m");
        System.out.println("\n\n\n\n\n");
        startGame();
    }

    public char[] createEncodedCharWord(int length) {
        char[] wordInCharsEncoded = new char[length];
        Arrays.fill(wordInCharsEncoded, '_');
        return wordInCharsEncoded;
    }

    public char enterTheLetter(BufferedReader reader) {
        boolean checkEnteredLetter = false;
        char letter = ' ';
        while (!checkEnteredLetter) {
            System.out.println("Enter the letter ");
            String letterStr;
            try {
                letterStr = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (letterStr.length() > 1 || letterStr.isBlank())
                System.out.println("You enter more than 1 letter or your letter is blank please try again");
            else if (enteredLetters.contains(letterStr.toCharArray()[0]))
                System.out.println("This letter was used please try again");
            else {
                letter = letterStr.toCharArray()[0];
                enteredLetters.add(letter);
                checkEnteredLetter = true;
            }
        }
        return letter;
    }

    public char[] checkLetterIntTheWord(char letter, char[] encodedWord, char[] word) {
        int numberOfLetterInEncodedWord = 0;
        for (int i = 0; i < encodedWord.length; ++i) {
            if (letter == word[i]) {
                encodedWord[i] = letter;
                ++numberOfLetterInEncodedWord;
            }
        }
        if (numberOfLetterInEncodedWord == 0) {
            player.decreaseLife();
        }
        return encodedWord;
    }

    private void checkWinCondition(char[] encodedWord) {
        for (char c : encodedWord) {
            if (c == '_') {
                return;
            }
        }
        System.out.println("\u001B[32mYou win this game\u001B[0m");
        System.out.println("\033[34mThe hidden word is " + word + "\033[0m");
        System.out.println("\n\n\n\n\n");
        startGame();
    }
}
