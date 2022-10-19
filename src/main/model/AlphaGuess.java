package model;

import java.util.ArrayList;

// Represents a guess made by the user
public class AlphaGuess extends Guess {
    //REQUIRES: user input length to be equal to Password.LENGTH
    //EFFECTS: creates a new guess using keyboard input from the user
    //         hint starts as an empty string
    //         char correct and in correct position start at 0
    public AlphaGuess(String userInput) {
        super();
        for (int i = 0; i < AlphaPassword.LENGTH; i++) {
            content.add(userInput.charAt(i));
        }
    }
}
