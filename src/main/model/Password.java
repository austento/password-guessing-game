package model;

import java.util.ArrayList;

// Represents a password for the user to guess
public class Password {
    public static final int LENGTH = 6;

    private int charGuessed;
    private int charNotGuessed;
    private ArrayList<Character> passwordContent;
    private boolean isGuessed;

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

    public ArrayList getPasswordContent() {
        return new ArrayList<>();
    }

    public boolean getIsGuessed() {
        return false;
    }

    public void setIsGuessed() {
        // stub
    }

    // REQUIRES: 0 < correctChar >= LENGTH
    // MODIFIES: this
    // EFFECTS: updates the number of guessed characters in the password
    //          removes # of guessed characters from charNotGuessed
    public void updateCharGuessed(int correctChar) {
        // stub
    }

}
