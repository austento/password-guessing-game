package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Element.Colour.GREEN;
import static model.Element.Colour.YELLOW;
import static org.junit.jupiter.api.Assertions.*;

public class TestElement {
    Element testE1;
    Element testE2;

    @BeforeEach
    void runBefore() {
        testE1 = new Element("a",1);
        testE2 = new Element("b", 2);
    }

    @Test
    void testConstructor() {
        assertEquals('a',testE1.getCharacter());
        assertEquals(1, testE1.getLocation());
    }

    @Test
    void testEqualsNotEqual() {
        assertNotEquals(testE1, testE2);
    }

    @Test
    void testEqualsDifferentCharacter() {
        Element testE3 = new Element("b",1);
        assertNotEquals(testE1, testE3);
    }

    @Test
    void testEqualsDifferentLocation() {
        Element testE3 = new Element("a",2);
        assertNotEquals(testE1, testE3);
    }

    @Test
    void testEquals() {
        Element testE3 = new Element("b",2);
        assertEquals(testE2, testE3);
    }

    @Test
    void testEqualDifferentDisplayColours() {
        testE1.setDisplayColour(GREEN);
        Element testE3 = new Element("a",1);
        testE3.setDisplayColour(Element.Colour.YELLOW);

        assertEquals(GREEN, testE1.getDisplayColour());
        assertEquals(YELLOW, testE3.getDisplayColour());

        assertEquals(testE3,testE1);
    }
}
