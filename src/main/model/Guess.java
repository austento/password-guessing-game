package model;

import java.util.ArrayList;
import model.Password;

// Represents a guess made by the user
public class Guess {
    private ArrayList<Character> guessContent;
    private int numCharactersCorrect;
    private int numCharactersCorrectPos;
    private String hint;

    // REQUIRES: user input length to be equal to Password.LENGTH
    // EFFECTS: creates a new guess using keyboard input from the user
    //          hints starts as an empty string
    public Guess() {
        // stub
    }

    public String getHint() {
        return " ";
        // stub
    }

    public int getNumCharactersCorrect() {
        return 0;
    }

    public int getNumCharactersCorrectPos() {
        return 0;
    }

    public void compareToPassword(Password pass) {
        // stub
    }

    public void updateHint() {
        // stub
    }
}
