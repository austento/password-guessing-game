package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.*;


public class Password extends Sequence implements Writable {
    public enum Type { NUMERIC, ALPHABETIC, NUMALPHA, ASCII }

    protected boolean guessed;
    protected List<Character> passwordDisplay;

    //EFFECTS: creates a new password
    //         guessed is set to false
    //         a new passwordDisplay is created
    public Password(Type passwordType) {
        super(randomizePasswordContent(passwordType));
        guessed = false;
        passwordDisplay = createDisplay();
        contentAsElements = contentToElementList();
    }

    public static String randomizePasswordContent(Type passwordType) {
        String result = "random";
        switch (passwordType) {
            case NUMERIC:
                result = randomNumeric(LENGTH);
                break;
            case ALPHABETIC:
                result = randomAlphabetic(LENGTH).toLowerCase();
                break;
            case NUMALPHA:
                result = randomAlphanumeric(LENGTH).toLowerCase();
                break;
            case ASCII:
                result = randomAscii(LENGTH).toLowerCase();
                break;
        }
        return result;
    }

    public String getPasswordContent() {
        return content;
    }

    public void setPasswordContent(String userInput) {
        content = userInput;
        contentAsElements = contentToElementList();
    }

    public boolean getIsGuessed() {
        return guessed;
    }

    public void setIsGuessed(boolean value) {
        guessed = value;
    }

    public List<Character> getPasswordDisplay() {
        return passwordDisplay;
    }

    //EFFECTS: creates a new list that represents which parts of the password have been guessed
    public List<Character> createDisplay() {
        List<Character> display = new ArrayList<>();

        for (int i = 0; i < Sequence.LENGTH; i++) {
            display.add('*');
        }

        return display;
    }

    //MODIFIES: this
    //EFFECTS: adds a character to the passwordDisplay
    public void revealCharacter(int index, char character) {
        passwordDisplay.set(index, character);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("guessed", guessed);
        json.put("content", content);

        return json;
    }
}
