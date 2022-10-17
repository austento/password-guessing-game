package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGuess {
    private Guess testGuess;
    private Password testPass;

    @BeforeEach
    void runBefore() {
        testGuess = new Guess("abcdef");
        testPass = new Password();
    }

    @Test
    void testUpdateHintNoneCorrect() {
        testGuess.updateHint();
        assertEquals("None of those characters are in the password", testGuess.getHint());
    }

    @Test
    void testUpdateHintNoPos() {
        testGuess.setNumCharactersCorrect(2);
        testGuess.updateHint();
        assertEquals("2 of those characters are in the password, but none are in their correct position",
                testGuess.getHint());
    }

    @Test
    void testUpdateHintCorrectPos() {
        testGuess.setNumCharactersCorrect(4);
        testGuess.setNumCharactersCorrectPos(3);
        testGuess.updateHint();
        assertEquals("4 of those characters are in the password, and 3 are in their correct position" ,
                testGuess.getHint());
    }

    @Test
    void testCompareNoMatches() {
        testPass.setPasswordContent("ghijkl");
        testGuess.compareToPassword(testPass);
        assertEquals(0, testGuess.getNumCharactersCorrect());
        assertEquals(0, testGuess.getNumCharactersCorrectPos());
    }

    @Test
    void testCompareNoPositionMatches() {
        testPass.setPasswordContent("zzzabc");
        testGuess.compareToPassword(testPass);
        assertEquals(3, testGuess.getNumCharactersCorrect());
        assertEquals(0, testGuess.getNumCharactersCorrectPos());
    }

    @Test
    void testComparePositionMatches() {
        testPass.setPasswordContent("abzzzc");
        testGuess.compareToPassword(testPass);
        assertEquals(3, testGuess.getNumCharactersCorrect());
        assertEquals(2, testGuess.getNumCharactersCorrectPos());
    }

    @Test
    void testCompareFullMatch() {
        testPass.setPasswordContent("abcdef");
        testGuess.compareToPassword(testPass);
        assertTrue(testPass.getIsGuessed());
    }

    @Test
    void testToString() {
        testPass.setPasswordContent("abzzzc");
        testGuess.compareToPassword(testPass);
        testGuess.updateHint();
        assertEquals(testGuess.toString(), "You guessed: [a, b, c, d, e, f]"
                + "\n" + "3 of those characters are in the password, and 2 are in their correct position");
    }

}
