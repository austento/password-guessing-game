package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class TestAlphaPassword {
    private AlphaPassword pw;
    private int exampleNum;

    @BeforeEach
    void runBefore() {
        pw = new AlphaPassword();
        exampleNum = AlphaPassword.LENGTH - 1;
    }

    @Test
    void testPasswordConstructor() {
        assertEquals(0, pw.getCharGuessed());
        assertEquals(AlphaPassword.LENGTH, pw.getCharNotGuessed());
        assertEquals(AlphaPassword.LENGTH, pw.getPasswordContent().size());
    }

    @Test
    void testUpdateCharGuessed() {
        pw.updateCharGuessed(exampleNum);
        assertEquals(exampleNum, pw.getCharGuessed());
        assertEquals(AlphaPassword.LENGTH - exampleNum, pw.getCharNotGuessed());
    }

    @Test
    void testSetPasswordContent() {
        ArrayList<Character> test = new ArrayList<>();
        test.add('a');
        test.add('b');
        test.add('c');
        test.add('d');
        test.add('e');
        test.add('f');
        pw.setPasswordContent("abcdef");
        assertEquals(test, pw.getPasswordContent());
    }
}