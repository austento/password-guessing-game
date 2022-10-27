package persistence;

import model.Guess;
import model.Password;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGuess(ArrayList<Character> content,
                              int numCharCorrect, int numCharCorrectPos, String hint, Guess guess) {
        assertEquals(content, guess.getGuessContent());
        assertEquals(numCharCorrect, guess.getNumCharCorrect());
        assertEquals(numCharCorrectPos, guess.getNumCharCorrectPos());
        assertEquals(hint, guess.getHint());
    }

    protected void checkPassword(ArrayList<Character> content, int charGuessed, int charNotGuessed,
                                 boolean isGuessed, Password password) {
        assertEquals(content, password.getPasswordContent());
        assertEquals(charGuessed, password.getCharGuessed());
        assertEquals(charNotGuessed, password.getCharNotGuessed());
        assertEquals(isGuessed, password.getIsGuessed());
    }
}
