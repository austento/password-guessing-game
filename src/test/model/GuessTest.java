package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GuessTest {
    private Guess testGuess;
    private int numCorrect;
    private int numCorrectPos;
    private ArrayList guessContent;
    private String hint;
    private Password testPass;

    @BeforeEach
    void runBefore() {
        testGuess = new Guess("abcdef");
        numCorrect = testGuess.getNumCharactersCorrect();
        numCorrectPos = testGuess.getNumCharactersCorrectPos();
        guessContent = testGuess.getGuessContent();
        hint = testGuess.getHint();
        testPass = new Password();
    }

    @Test
    void testUpdateHintNoneCorrect() {
        testGuess.updateHint();
        assertEquals("You have no correct characters", hint);
    }

    @Test
    void testUpdateHintNoPos() {
        testGuess.setNumCharactersCorrect(2);
        testGuess.updateHint();
        assertEquals("You have 2 correct characters, but none are in the correct position", hint);
    }

    @Test
    void testUpdateHintCorrectPos() {
        testGuess.setNumCharactersCorrect(4);
        testGuess.setNumCharactersCorrectPos(3);
        testGuess.updateHint();
        assertEquals("You have 4 correct characters, and 3 are in the correct position" , hint);
    }

    @Test
    void testCompareNoMatches() {
        testPass.setPasswordContent("ghijkl");
        testGuess.compareToPassword(testPass);
        assertEquals(0, numCorrect);
        assertEquals(0, numCorrectPos);
    }

    @Test
    void testCompareNoPositionMatches() {
        testPass.setPasswordContent("zzzabc");
        testGuess.compareToPassword(testPass);
        assertEquals(3, numCorrect);
        assertEquals(0, numCorrect);
    }

    @Test
    void testComparePositionMatches() {
        testPass.setPasswordContent("abzzzc");
        testGuess.compareToPassword(testPass);
        assertEquals(3, numCorrect);
        assertEquals(2,numCorrectPos);
    }

    @Test
    void testCompareFullMatch() {
        testPass.setPasswordContent("abcdef");
        testGuess.compareToPassword(testPass);
        assertTrue(testPass.getIsGuessed());
    }

}
