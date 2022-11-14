package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

public class Guess extends Sequence implements Writable {
    protected String hint;

    public Guess(String userInput) {
        super(userInput);
        hint = "";
        contentAsElements = contentToElementList();
    }

    public String getHint() {
        return hint;
    }

    public String getGuessContent() {
        return content;
    }

    public List<Element> getContentAsElements() {
        return contentAsElements;
    }

    //MODIFIES: this
    //          Password
    //EFFECTS: compares this Guess to Password passed as param
    //         if password and guess match completely, sets password status to guessed
    //         updates Password display as elements of password are guessed
    //         updates how guess will be displayed to user
    public void compareToPassword(Password pass) {
        if (content.equals(pass.getPasswordContent())) {
            pass.setIsGuessed(true);
        } else {
            List<Character> guessableCharacters = pass.contentToList();
            checkForCorrectLocation(guessableCharacters, pass);
            checkForCorrectCharacter(guessableCharacters, pass);
        }
        updateHint();
    }

    //MODIFIES: this
    //          Password
    //EFFECTS: checks if any characters in the guess are in the correct location
    public void checkForCorrectLocation(List<Character> guessableCharacters, Password password) {
        for (Element element : contentAsElements) {
            int index = contentAsElements.indexOf(element);
            Element passElement = password.contentAsElements.get(index);

            if (element.equals(passElement)) {
                guessableCharacters.remove(element.getCharacter());
                password.revealCharacter(index, element.getCharacter());
                element.setDisplayColour(Element.Colour.GREEN);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if any characters in the guess appear at any location in the password
    //         if multiple of the same character is in the guess, but only one is in the password, only the first
    //         instance of the character counts as appearing in the password
    public void checkForCorrectCharacter(List<Character> guessableCharacters, Password password) {
        for (Element element : contentAsElements) {
            if (element.getDisplayColour() == null) {
                if (guessableCharacters.contains(element.getCharacter())) {
                    guessableCharacters.remove(element.getCharacter());
                    element.setDisplayColour(Element.Colour.YELLOW);
                } else {
                    element.setDisplayColour(Element.Colour.RED);
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a String using numCharactersCorrect and numCharactersCorrectPos
    //         sets hint as created string
    public void updateHint() {
        StringBuilder result = new StringBuilder();

        for (Element element : contentAsElements) {
            result.append(element.toString());
        }

        hint = result.toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("content", content);

        return json;
    }
}
