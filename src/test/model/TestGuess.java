package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGuess {
    private Guess testGuess;
    private Guess testGuess2;
    private Password testPass;

    @BeforeEach
    void runBefore() {
        testGuess = new Guess("abcdef");
        testGuess2 = new Guess("wwwduk");
        testPass = new Password(Password.Type.ALPHABETIC);
    }

    @Test
    void testConstructor() {
        assertEquals(Sequence.LENGTH, testGuess.getGuessContent().length());
        assertEquals("", testGuess.getHint());

        assertEquals(Sequence.LENGTH, testGuess.contentAsElements.size());
        assertTrue(testGuess.contentAsElements.contains(new Element("a",0)));
        assertTrue(testGuess.contentAsElements.contains(new Element("b",1)));
        assertTrue(testGuess.contentAsElements.contains(new Element("c",2)));
        assertTrue(testGuess.contentAsElements.contains(new Element("d",3)));
        assertTrue(testGuess.contentAsElements.contains(new Element("e",4)));
        assertTrue(testGuess.contentAsElements.contains(new Element("f",5)));
    }

    @Test
    void testNoMatches() {
        testPass.setPasswordContent("ghijkl");
        testGuess.compareToPassword(testPass);

        assertEquals("a( RED )b( RED )c( RED )d( RED )e( RED )f( RED )",testGuess.getHint());
    }

    @Test
    void testOnlyCharacterMatches() {
        testPass.setPasswordContent("defabc");
        testGuess.compareToPassword(testPass);

        assertEquals("a( YELLOW )b( YELLOW )c( YELLOW )d( YELLOW )e( YELLOW )f( YELLOW )",testGuess.getHint());
    }

    @Test
    void testMultipleSameCharacterInGuessOneInPassword() {
        testPass.setPasswordContent("dwdduk");
        testGuess2.compareToPassword(testPass);

        assertEquals("w( RED )w( GREEN )w( RED )d( GREEN )u( GREEN )k( GREEN )",testGuess2.getHint());
    }

    @Test
    void testFullMatch() {
        testPass.setPasswordContent("abcdef");
        testGuess.compareToPassword(testPass);

        assertTrue(testPass.getIsGuessed());
    }
}
