package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGuess {
    private Guess testGuess;
    private Guess testGuess2;
    private Password testPass;
    private List<String> events;

    @BeforeEach
    void runBefore() {
        testGuess = new Guess("abcdef");
        testGuess2 = new Guess("wwwduk");
        testPass = new Password(Password.Type.ALPHABETIC);
        EventLog.getInstance().clear();
        events = new ArrayList<>();
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
    void testConstructorEventAddedToLog() {
        new Guess("abcdef");

        EventLog el = EventLog.getInstance();
        for (Event event: el) {
            events.add(event.getDescription());
        }

        assertTrue(events.contains("New guess added: abcdef"));
    }

    @Test
    void testElementsToList() {
        Element e1 = new Element("a",0);
        Element e2 = new Element("b",1);
        Element e3 = new Element("c",2);
        Element e4 = new Element("d",3);
        Element e5 = new Element("e",4);
        Element e6 = new Element("f",5);

        List<Element> elementList = new ArrayList<>();
        elementList.add(e1);
        elementList.add(e2);
        elementList.add(e3);
        elementList.add(e4);
        elementList.add(e5);
        elementList.add(e6);

        assertEquals(elementList,testGuess.getContentAsElements());
    }

    @Test
    void testNoMatches() {
        testPass.setPasswordContent("ghijkl");
        testGuess.compareToPassword(testPass);

        assertEquals("a( RED )b( RED )c( RED )d( RED )e( RED )f( RED )",testGuess.getHint());

        EventLog el = EventLog.getInstance();
        for (Event event: el) {
            events.add(event.getDescription());
        }

        assertTrue(events.contains("Guess: abcdef added to past guesses"));
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

        EventLog el = EventLog.getInstance();
        for (Event event: el) {
            events.add(event.getDescription());
        }

        assertTrue(events.contains("Password: abcdef was guessed"));
    }
}
