package ui;

import model.Guess;
import model.Password;
import model.Sequence;
import org.json.JSONArray;
import org.json.JSONObject;
import model.persistence.JsonWriter;
import model.persistence.Writable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a playable game where user can randomly generate a password and guess it
public class PasswordGame implements Writable {
    public static final String JSON_STORE = "./data/passwordgame.json";

    private Password password;
    private List<Guess> pastGuesses;
    private Scanner input;
    private String userInput;
    private JsonWriter jsonWriter;
    private boolean continueGame;



    //EFFECTS: constructs a new password game
    public PasswordGame() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        userInput = null;
        password = new Password(Password.Type.NUMERIC);
        pastGuesses = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        continueGame = true;
    }

    //MODIFIES: this
    //EFFECTS: chooses which type of password to generate based on user input
    public void choosePasswordType() {
        displayPasswordTypeMenu();
        userInput = input.next();
        switch (userInput) {
            case "1":
                password = new Password(Password.Type.NUMERIC);
                break;
            case "2":
                password = new Password(Password.Type.ALPHABETIC);
                break;
            case "3":
                password = new Password(Password.Type.NUMALPHA);
                break;
            case "4":
                password = new Password(Password.Type.ASCII);
                break;
        }
    }

    //EFFECTS: displays menu with types of passwords to choose
    private void displayPasswordTypeMenu() {
        System.out.println("\nWhat kind of password would you like to generate?");
        System.out.println("\t1 --> numeric (could only contain numbers 1-9)");
        System.out.println("\t2 --> alphabetical (could only contain the 26 letters in the alphabet");
        System.out.println("\t3 --> alphabetical + numeric (could contain numbers 1-9 and 26 letters in the alphabet");
        System.out.println("\t4 --> ascii (could contain any ascii character - i.e. $, %, etc)");
    }

    //EFFECTS: displays the in-game menu
    private void displayGameMenu() {
        System.out.println("\nTry to guess the password!");
        System.out.println("\nPassword: " + password.getPasswordDisplay().toString());
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

    //MODIFIES: this
    //EFFECTS: creates a new guess using the user's input
    //         compares guess to password
    //         stops game if password is guessed
    private void makeAGuess() {
        System.out.println("Please enter your " + Sequence.LENGTH + " character guess:");
        userInput = input.next();
        if (userInput.length() < Sequence.LENGTH) {
            System.out.println("Your guess has to be " + Sequence.LENGTH + " characters long!");
        } else {
            Guess currentGuess = new Guess(userInput);
            currentGuess.compareToPassword(password);
            if (!password.getIsGuessed()) {
                currentGuess.updateHint();
                pastGuesses.add(currentGuess);
                System.out.println(currentGuess.getHint());
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
        Guess guess = new Guess(content);
        guess.compareToPassword(password);
        pastGuesses.add(guess);
    }

    //EFFECTS: displays each of the past guesses a user has made
    //         if no guesses exist, tells the user they have not made any guesses
    private void displayPastGuesses() {
        if (pastGuesses.size() == 0) {
            System.out.println("You have not made any guesses!");
        } else {
            System.out.println("These are your past guesses:");
            for (Guess pastGuess : pastGuesses) {
                System.out.println(pastGuess.getHint());
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

    //EFFECTS: adds pastGuesses to data being saved.
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

    public void setPassword(Password password) {
        this.password.setPasswordContent(password.getPasswordContent());
    }

    public List<Guess> getPastGuesses() {
        return pastGuesses;
    }

    public void setPastGuesses(List<Guess> pastGuesses) {
        this.pastGuesses = pastGuesses;
    }
}

