package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

public class Password extends Sequence implements Writable {
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

    public void setCharGuessed(int charGuessed) {
        this.charGuessed = charGuessed;
    }

    public int getCharNotGuessed() {
        return charNotGuessed;
    }

    public void setCharNotGuessed(int charNotGuessed) {
        this.charNotGuessed = charNotGuessed;
    }

    public ArrayList<Character> getPasswordContent() {
        return content;
    }

    //MODIFIES: this
    //EFFECTS: sets the password content to a specific sequence
    public void setPasswordContent(String userInput) {
        content.clear();
        for (int i = 0; i < userInput.length(); i++) {
            content.add(i, userInput.charAt(i));
        }
    }

    public void setPasswordContent(ArrayList<Character> content) {
        this.content = content;
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
        json.put("content", contentToString());

        return json;
    }
}
