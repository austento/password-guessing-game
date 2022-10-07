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
    //          hint starts as an empty string
    public Guess(String userInput) {
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

    public ArrayList getGuessContent() {
        return new ArrayList<>();
    }

    public void setNumCharactersCorrect(int num) {
        // stub
    }

    public void setNumCharactersCorrectPos(int num) {
        // stub
    }

    // MODIFIES: this
    //           Password
    // EFFECTS: compares guessContent to passwordContent of Password passed as param
    //          updates numCharactersCorrect based on how many characters are correct
    //          updates numCharactersCorrectPos based on how many char in the right position
    //          if password and guess match completely, sets password status to guessed
    public void compareToPassword(Password pass) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: creates a String using info from numCharactersCorrect, numCharactersCorrectPos
    //          sets hint as created string
    public void updateHint() {
        // stub
    }
}
