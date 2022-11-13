package persistence;

import model.Guess;
import model.Password;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGuess(String content, String hint, Guess guess) {
        assertEquals(content, guess.getGuessContent());
        assertEquals(hint, guess.getHint());
    }

    protected void checkPassword(String content, boolean guessed, Password password) {
        assertEquals(content, password.getPasswordContent());
        assertEquals(guessed, password.getIsGuessed());
    }
}
