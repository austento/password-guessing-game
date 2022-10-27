package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public abstract class Guess extends Sequence implements Writable {
    protected int numCharCorrect;
    protected int numCharCorrectPos;
    protected String hint;

    public Guess() {
        numCharCorrect = 0;
        numCharCorrectPos = 0;
        hint = "";
    }

    public String getHint() {
        return hint;
    }

    public int getNumCharCorrect() {
        return numCharCorrect;
    }

    public int getNumCharCorrectPos() {
        return numCharCorrectPos;
    }

    public ArrayList<Character> getGuessContent() {
        return content;
    }

    public void setNumCharCorrect(int num) {
        numCharCorrect = num;
    }

    public void setNumCharCorrectPos(int num) {
        numCharCorrectPos = num;
    }

    //MODIFIES: this
    //          Password
    //EFFECTS: compares guessContent to passwordContent of Password passed as param
    //         updates numCharactersCorrect based on how many characters are correct
    //         updates numCharactersCorrectPos based on how many char in the right position
    //         if password and guess match completely, sets password status to guessed
    public void compareToPassword(AlphaPassword pass) {
        if (content.equals(pass.getPasswordContent())) {
            pass.setIsGuessed(true);
        } else {
            for (int i = 0; i < AlphaPassword.LENGTH; i++) {
                char guessChar = content.get(i);
                if (pass.getPasswordContent().contains(guessChar)) {
                    numCharCorrect++;
                    if (pass.getPasswordContent().get(i) == guessChar) {
                        numCharCorrectPos++;
                    }
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a String using numCharactersCorrect and numCharactersCorrectPos
    //         sets hint as created string
    public void updateHint() {
        if (numCharCorrect == 0) {
            hint = "None of those characters are in the password";
        } else {
            hint = numCharCorrect + " of those characters are in the password,";
            if (numCharCorrectPos == 0) {
                hint = hint + " but none are in their correct position";
            } else {
                hint = hint + " and " + numCharCorrectPos + " are in their correct position";
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a String representation of a Guess object
    @Override
    public String toString() {
        return "You guessed: " + content.toString() + "\n" + hint;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numCharCorrect", numCharCorrect);
        json.put("numCharCorrectPos", numCharCorrectPos);
        json.put("hint",hint);
        json.put("content", content);

        return json;
    }
}
