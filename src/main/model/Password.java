package model;

import java.util.ArrayList;
import java.util.Random;

// Represents a password for the user to guess
public class Password {
    public static final int LENGTH = 6;

    private int charGuessed;
    private int charNotGuessed;
    private ArrayList<Character> passwordContent;
    private boolean isGuessed;

    // EFFECTS: creates a new password
    //          charGuessed set to 0
    //          charNotGuessed set to LENGTH
    //          isGuessed set to false
    // *Need source for random character generator
    public Password() {
        charGuessed = 0;
        charNotGuessed = LENGTH;
        isGuessed = false;
        passwordContent = new ArrayList<Character>();

        for (int i = 0; i < LENGTH; i++) {
            Random r = new Random();
            Character randChar = (char) (r.nextInt(26) + 'a');
            passwordContent.add(randChar);
        }
    }

    public int getCharGuessed() {
        return charGuessed;
    }

    public int getCharNotGuessed() {
        return charNotGuessed;
    }

    public ArrayList<Character> getPasswordContent() {
        return passwordContent;
    }

    public void setPasswordContent(String userInput) {
        for (int i = 0; i < userInput.length(); i++) {
            passwordContent.set(i, userInput.charAt(i));
        }
    }

    public boolean getIsGuessed() {
        return isGuessed;
    }

    public void setIsGuessed(boolean value) {
        isGuessed = value;
    }

    // REQUIRES: 0 < correctChar >= LENGTH
    // MODIFIES: this
    // EFFECTS: updates the number of guessed characters in the password
    //          removes # of guessed characters from charNotGuessed
    public void updateCharGuessed(int correctChar) {
        charGuessed = charGuessed + correctChar;
        charNotGuessed = charNotGuessed - correctChar;
    }

}
