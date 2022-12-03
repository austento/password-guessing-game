package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestPassword {
    private Password testNumPass;
    private Password testAlphaPass;
    private Password testAlphaNumPass;
    private Password testAsciiPass;
    private List<String> events;

    @BeforeEach
    void runBefore() {
        testNumPass = new Password(Password.Type.NUMERIC);
        testAlphaPass = new Password(Password.Type.ALPHABETIC);
        testAlphaNumPass = new Password(Password.Type.NUMALPHA);
        testAsciiPass = new Password(Password.Type.ASCII);
        events = new ArrayList<>();
        EventLog.getInstance().clear();
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
    void testConstructorEventAddToLog() {
        Password password = new Password(Password.Type.NUMERIC);

        EventLog el = EventLog.getInstance();
        for (Event event: el) {
            events.add(event.getDescription());
        }

        assertTrue(events.contains("Password of type NUMERIC and content " + password.content + " created"));
    }

    @Test
    void testSetPasswordContent() {
        testAlphaPass.setPasswordContent("abcdef");
        assertEquals("abcdef", testAlphaPass.getPasswordContent());
    }

    @Test
    void testRevealChar() {
        testAlphaPass.revealCharacter(0, 'a');
        assertEquals("[a, *, *, *, *, *]", testAlphaPass.getPasswordDisplay().toString());

        EventLog el = EventLog.getInstance();
        for (Event event: el) {
            events.add(event.getDescription());
        }

        assertTrue(events.contains("Element: a0 was guessed"));
    }
}