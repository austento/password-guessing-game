package persistence;

import model.Guess;
import model.Password;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGuess(String content,
                              int numCharCorrect, int numCharCorrectPos, String hint, Guess guess) {
        assertEquals(content, guess.contentToString());
        assertEquals(numCharCorrect, guess.getNumCharCorrect());
        assertEquals(numCharCorrectPos, guess.getNumCharCorrectPos());
        assertEquals(hint, guess.getHint());
    }

    protected void checkPassword(String content, int charGuessed, int charNotGuessed,
                                 boolean isGuessed, Password password) {
        assertEquals(content, password.contentToString());
        assertEquals(charGuessed, password.getCharGuessed());
        assertEquals(charNotGuessed, password.getCharNotGuessed());
        assertEquals(isGuessed, password.getIsGuessed());
    }
}
