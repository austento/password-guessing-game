package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.*;

// Represents a password for the user to guess - can have one of four types
public class Password extends Sequence implements Writable {
    public enum Type { NUMERIC, ALPHABETIC, NUMALPHA, ASCII }

    protected boolean guessed;
    protected List<Character> passwordDisplay;

    //EFFECTS: creates a new randomized password
    //         guessed is set to false
    //         a new passwordDisplay is created
    public Password(Type passwordType) {
        super(randomizePasswordContent(passwordType));
        guessed = false;
        passwordDisplay = createDisplay();
        contentAsElements = contentToElementList();
        EventLog.getInstance().logEvent(new Event("Password of type " + passwordType + " and content "
                + content + " created"));
    }

    //EFFECTS: chooses which kind of password to randomize and randomizes it
    //code adapted from: https://stackoverflow.com/questions/59990633/condition-coverage-on-switch-statement
    public static String randomizePasswordContent(Type passwordType) {
        if (passwordType == Type.NUMERIC) {
            return randomNumeric(LENGTH);
        } else if (passwordType == Type.ALPHABETIC) {
            return randomAlphabetic(LENGTH).toLowerCase();
        } else if (passwordType == Type.NUMALPHA) {
            return randomAlphanumeric(LENGTH).toLowerCase();
        } else {
            return randomAscii(LENGTH).toLowerCase();
        }
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
        EventLog.getInstance().logEvent(new Event("Element: " + character + index + " was guessed"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("guessed", guessed);
        json.put("content", content);

        return json;
    }
}
