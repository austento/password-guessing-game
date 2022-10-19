package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlphaGuess {
    private AlphaGuess testAlphaGuess;
    private AlphaPassword testPass;

    @BeforeEach
    void runBefore() {
        testAlphaGuess = new AlphaGuess("abcdef");
        testPass = new AlphaPassword();
    }

    @Test
    void testConstructor() {
        AlphaGuess testConstructorAlphaGuess = new AlphaGuess("ghijkl");
        ArrayList<Character> guessContent = new ArrayList<>();
        guessContent.add('g');
        guessContent.add('h');
        guessContent.add('i');
        guessContent.add('j');
        guessContent.add('k');
        guessContent.add('l');
        assertEquals(guessContent, testConstructorAlphaGuess.getGuessContent());
        assertEquals(6, testConstructorAlphaGuess.getGuessContent().size());
        assertEquals("", testConstructorAlphaGuess.getHint());
        assertEquals(0, testConstructorAlphaGuess.getNumCharCorrectPos());
        assertEquals(0, testConstructorAlphaGuess.getNumCharCorrect());

    }

    @Test
    void testUpdateHintNoneCorrect() {
        testAlphaGuess.updateHint();
        assertEquals("None of those characters are in the password", testAlphaGuess.getHint());
    }

    @Test
    void testUpdateHintNoPos() {
        testAlphaGuess.setNumCharCorrect(2);
        testAlphaGuess.updateHint();
        assertEquals("2 of those characters are in the password, but none are in their correct position",
                testAlphaGuess.getHint());
    }

    @Test
    void testUpdateHintCorrectPos() {
        testAlphaGuess.setNumCharCorrect(4);
        testAlphaGuess.setNumCharCorrectPos(3);
        testAlphaGuess.updateHint();
        assertEquals("4 of those characters are in the password, and 3 are in their correct position" ,
                testAlphaGuess.getHint());
    }

    @Test
    void testCompareNoMatches() {
        testPass.setPasswordContent("ghijkl");
        testAlphaGuess.compareToPassword(testPass);
        assertEquals(0, testAlphaGuess.getNumCharCorrect());
        assertEquals(0, testAlphaGuess.getNumCharCorrectPos());
    }

    @Test
    void testCompareNoPositionMatches() {
        testPass.setPasswordContent("zzzabc");
        testAlphaGuess.compareToPassword(testPass);
        assertEquals(3, testAlphaGuess.getNumCharCorrect());
        assertEquals(0, testAlphaGuess.getNumCharCorrectPos());
    }

    @Test
    void testComparePositionMatches() {
        testPass.setPasswordContent("abzzzc");
        testAlphaGuess.compareToPassword(testPass);
        assertEquals(3, testAlphaGuess.getNumCharCorrect());
        assertEquals(2, testAlphaGuess.getNumCharCorrectPos());
    }

    @Test
    void testCompareFullMatch() {
        testPass.setPasswordContent("abcdef");
        testAlphaGuess.compareToPassword(testPass);
        assertTrue(testPass.getIsGuessed());
    }

    @Test
    void testToString() {
        testPass.setPasswordContent("abzzzc");
        testAlphaGuess.compareToPassword(testPass);
        testAlphaGuess.updateHint();
        assertEquals(testAlphaGuess.toString(), "You guessed: [a, b, c, d, e, f]"
                + "\n" + "3 of those characters are in the password, and 2 are in their correct position");
    }

}
