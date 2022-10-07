package model;

import java.util.ArrayList;

// Represents a password for the user to guess
public class Password {
    public static final int LENGTH = 6;

    private int charGuessed;
    private int charNotGuessed;
    private ArrayList<Character> passwordContent;

    // EFFECTS: creates a new password
    //          charGuessed is set to 0
    //          charNotGuessed is set to LENGTH
    public Password() {
        // stub
    }

    public int getCharGuessed() {
        return 0;
        // stub
    }

    public int getCharNotGuessed() {
        return 0;
        // stub
    }

    // MODIFIES: this
    // EFFECTS: updates the number of guessed characters in the password
    public void updateCharGuessed() {
        // stub
    }

}
