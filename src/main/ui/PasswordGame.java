package ui;

import model.AlphaGuess;
import model.AlphaPassword;
import model.Guess;
import model.Password;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordGame implements Writable {
    public static final String JSON_STORE = "./data/passwordgame.json";

    private AlphaPassword password;
    private ArrayList<AlphaGuess> pastGuesses;
    private Scanner input;
    private String userInput;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean continueGame;



    //EFFECTS: constructs a new password game
    public PasswordGame() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        userInput = null;
        password = new AlphaPassword();
        pastGuesses = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        continueGame = true;
    }

    //EFFECTS: displays the in-game menu
    private void displayGameMenu() {
        System.out.println("\nTry to guess the password!");
        System.out.println("\tg --> make a guess");
        System.out.println("\td --> show past guesses");
        System.out.println("\ts --> save my progress for later and return to main menu");
        System.out.println("\tm --> give up and return to main menu");
    }

    //MODIFIES: this
    //EFFECTS: runs a new game
    //         creates a new password for the user to guess
    //         processes user input related to the game
    public void runNewGame() {
        while (continueGame) {
            displayGameMenu();
            userInput = input.next();
            switch (userInput) {
                case "g":
                    makeAGuess();
                    break;
                case "d":
                    displayPastGuesses();
                    break;
                case "s":
                    savePasswordGame();
                    continueGame = false;
                    break;
                case "m":
                    System.out.println("You have given up on the password" + "\nThe password was "
                            + password.getPasswordContent());
                    continueGame = false;
                    break;
            }
        }
    }

    //EFFECTS: saves password game to file
    public void savePasswordGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }

//    //MODIFIES: this
//    //EFFECTS: loads password game from file
//    public void loadPasswordGame() {
//        try {
//            jsonReader.read();
//        } catch (IOException e) {
//            System.out.println();
//        }
//    }

    //MODIFIES: this
    //EFFECTS: creates a new guess using the user's input
    //         compares guess to password
    //         stops game if password is guessed
    private void makeAGuess() {
        System.out.println("Please enter your " + AlphaPassword.LENGTH + " character guess:");
        userInput = input.next();
        if (userInput.length() < AlphaPassword.LENGTH) {
            System.out.println("Your guess has to be " + AlphaPassword.LENGTH + " characters long!");
        } else {
            AlphaGuess currentGuess = new AlphaGuess(userInput);
            currentGuess.compareToPassword(password);
            if (!password.getIsGuessed()) {
                currentGuess.updateHint();
                pastGuesses.add(currentGuess);
                System.out.println(currentGuess);
            } else {
                System.out.println("You have guessed the password!");
                continueGame = false;
                displayScore();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: makeAGuess with provided string as guess content
    public void makeAGuess(String content) {
        AlphaGuess guess = new AlphaGuess(content);
        guess.compareToPassword(password);
        guess.updateHint();
        pastGuesses.add(guess);
    }

    //EFFECTS: displays each of the past guesses a user has made
    //         if no guesses exist, tells the user they have not made any guesses
    private void displayPastGuesses() {
        if (pastGuesses.size() == 0) {
            System.out.println("You have not made any guesses!");
        } else {
            System.out.println("These are your past guesses:");
            for (AlphaGuess pastGuess : pastGuesses) {
                System.out.println(pastGuess);
            }
        }
    }

    //not complete - want to make more complex
    //EFFECTS: calculates and displays a score
    private void displayScore() {
        double score = 100;
        score -= pastGuesses.size();
        System.out.println("Your score is: " + score);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("password", password.toJson());
        json.put("pastGuesses", pastGuessesToJson());

        return json;
    }

    private JSONArray pastGuessesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Guess g : pastGuesses) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }

    public Password getPassword() {
        return password;
    }

    public ArrayList<AlphaGuess> getPastGuesses() {
        return pastGuesses;
    }
}

