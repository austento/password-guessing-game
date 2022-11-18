package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestPassword {
    private Password testNumPass;
    private Password testAlphaPass;
    private Password testAlphaNumPass;
    private Password testAsciiPass;

    @BeforeEach
    void runBefore() {
        testNumPass = new Password(Password.Type.NUMERIC);
        testAlphaPass = new Password(Password.Type.ALPHABETIC);
        testAlphaNumPass = new Password(Password.Type.NUMALPHA);
        testAsciiPass = new Password(Password.Type.ASCII);
    }

    @Test
    void testPasswordConstructor() {
        assertEquals(Sequence.LENGTH, testNumPass.getPasswordContent().length());
        assertEquals(Sequence.LENGTH, testAlphaPass.getPasswordContent().length());
        assertEquals(Sequence.LENGTH, testAlphaNumPass.getPasswordContent().length());
        assertEquals(Sequence.LENGTH, testAsciiPass.getPasswordContent().length());

        assertFalse(testNumPass.getIsGuessed());
        assertFalse(testAlphaPass.getIsGuessed());
        assertFalse(testAlphaNumPass.getIsGuessed());
        assertFalse(testAsciiPass.getIsGuessed());

        assertEquals(Sequence.LENGTH, testNumPass.getPasswordDisplay().size());
    }

    @Test
    void testConstructorNoType() {
        Password testBadPass = new Password(null);
        assertEquals("random",testBadPass.content);
    }

    @Test
    void testSetPasswordContent() {
        testAlphaPass.setPasswordContent("abcdef");
        assertEquals("abcdef", testAlphaPass.getPasswordContent());
    }

    @Test
    void testRevealChar() {
        testAlphaPass.revealCharacter(0, 'a');
        List<Character> display = testAlphaPass.getPasswordDisplay();
        assertEquals("[a, *, *, *, *, *]", testAlphaPass.getPasswordDisplay().toString());
    }
}