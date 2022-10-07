package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class PasswordTest {
    private Password pw;
    private int charGuessed;
    private int charNotGuessed;
    private ArrayList passContent;
    private int exampleNum;

    @BeforeEach
    void runBefore() {
        pw = new Password();
        charGuessed = pw.getCharGuessed();
        charNotGuessed = pw.getCharNotGuessed();
        passContent = pw.getPasswordContent();
        exampleNum = Password.LENGTH - 1;
    }

    @Test
    void testPasswordConstructor() {
        assertEquals(0, charGuessed);
        assertEquals(Password.LENGTH, charNotGuessed);
        assertEquals(Password.LENGTH, passContent.size());
    }

    @Test
    void testUpdateCharGuessed() {
        pw.updateCharGuessed(exampleNum);
        assertEquals(exampleNum, charGuessed);
        assertEquals(Password.LENGTH - exampleNum,charNotGuessed);
    }
}