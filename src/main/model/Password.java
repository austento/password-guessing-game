package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

public abstract class Password extends Sequence implements Writable {
    protected int charGuessed;
    protected int charNotGuessed;
    protected boolean isGuessed;
    protected Random rand;

    public Password() {
        super();
        charGuessed = 0;
        charNotGuessed = LENGTH;
        isGuessed = false;
        rand = new Random();
    }

    public int getCharGuessed() {
        return charGuessed;
    }

    public int getCharNotGuessed() {
        return charNotGuessed;
    }

    public ArrayList<Character> getPasswordContent() {
        return content;
    }

    //MODIFIES: this
    //EFFECTS: sets the password content to a specific sequence
    public void setPasswordContent(String userInput) {
        for (int i = 0; i < userInput.length(); i++) {
            content.set(i, userInput.charAt(i));
        }
    }

    public boolean getIsGuessed() {
        return isGuessed;
    }

    public void setIsGuessed(boolean value) {
        isGuessed = value;
    }

    //REQUIRES: 0 < correctChar >= LENGTH
    //MODIFIES: this
    //EFFECTS: updates the number of guessed characters in the password
    //         removes # of guessed characters from charNotGuessed
    public void updateCharGuessed(int correctChar) {
        charGuessed += correctChar;
        charNotGuessed -= correctChar;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("charGuessed", charGuessed);
        json.put("charNotGuessed", charNotGuessed);
        json.put("isGuessed", isGuessed);
        json.put("content", content);

        return json;
    }
}
