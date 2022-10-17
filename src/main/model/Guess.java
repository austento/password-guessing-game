package model;

import java.util.ArrayList;

// Represents a guess made by the user
public class Guess {
    private ArrayList<Character> guessContent;
    private int numCharactersCorrect;
    private int numCharactersCorrectPos;
    private String hint;

    //REQUIRES: user input length to be equal to Password.LENGTH
    //EFFECTS: creates a new guess using keyboard input from the user
    //         hint starts as an empty string
    //         char correct and in correct position start at 0
    public Guess(String userInput) {
        guessContent = new ArrayList<>();
        numCharactersCorrect = 0;
        numCharactersCorrectPos = 0;
        hint = "";

        for (int i = 0; i < Password.LENGTH; i++) {
            guessContent.add(userInput.charAt(i));
        }
    }

    public String getHint() {
        return hint;
    }

    public int getNumCharactersCorrect() {
        return numCharactersCorrect;
    }

    public int getNumCharactersCorrectPos() {
        return numCharactersCorrectPos;
    }

    public ArrayList<Character> getGuessContent() {
        return guessContent;
    }

    public void setNumCharactersCorrect(int num) {
        numCharactersCorrect = num;
    }

    public void setNumCharactersCorrectPos(int num) {
        numCharactersCorrectPos = num;
    }

    //MODIFIES: this
    //          Password
    //EFFECTS: compares guessContent to passwordContent of Password passed as param
    //         updates numCharactersCorrect based on how many characters are correct
    //         updates numCharactersCorrectPos based on how many char in the right position
    //         if password and guess match completely, sets password status to guessed
    public void compareToPassword(Password pass) {
        if (guessContent.equals(pass.getPasswordContent())) {
            pass.setIsGuessed(true);
        } else {
            for (int i = 0; i < Password.LENGTH; i++) {
                char guessChar = guessContent.get(i);
                if (pass.getPasswordContent().contains(guessChar)) {
                    numCharactersCorrect++;
                    if (pass.getPasswordContent().get(i) == guessChar) {
                        numCharactersCorrectPos++;
                    }
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a String using numCharactersCorrect and numCharactersCorrectPos
    //         sets hint as created string
    public void updateHint() {
        if (numCharactersCorrect == 0) {
            hint = "None of those characters are in the password";
        } else {
            hint = numCharactersCorrect + " of those characters are in the password,";
            if (numCharactersCorrectPos == 0) {
                hint = hint + " but none are in their correct position";
            } else {
                hint = hint + " and " + numCharactersCorrectPos + " are in their correct position";
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a String representation of a Guess object
    @Override
    public String toString() {
        return "You guessed: " + guessContent.toString() + "\n" + hint;
    }
}
