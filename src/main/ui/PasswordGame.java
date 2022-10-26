package ui;

import model.AlphaGuess;
import model.AlphaPassword;

import java.util.ArrayList;
import java.util.Scanner;

public class PasswordGame {
    private AlphaPassword password;
    private ArrayList<AlphaGuess> pastGuesses;
    private Scanner input;
    private String userInput;

    //EFFECTS: constructs a new password game
    public PasswordGame() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        userInput = null;
        password = new AlphaPassword();
        pastGuesses = new ArrayList<>();
        runNewGame();
    }

    //EFFECTS: displays the in-game menu
    private void displayGameMenu() {
        System.out.println("\nTry to guess the password!");
        System.out.println("\tg --> make a guess");
        System.out.println("\td --> show past guesses");
        System.out.println("\tm --> give up and return to main menu");
    }

    //MODIFIES: this
    //EFFECTS: runs a new game
    //         creates a new password for the user to guess
    //         processes user input related to the game
    public void runNewGame() {
        while (!password.getIsGuessed()) {
            displayGameMenu();
            userInput = input.next();
            switch (userInput) {
                case "g":
                    makeAGuess();
                    break;
                case "d":
                    displayPastGuesses();
                    break;
                case "m":
                    System.out.println("You have given up on the password" + "\nThe password was "
                            + password.getPasswordContent());
                    password.setIsGuessed(true);
                    break;
            }
        }
    }

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
                displayScore();
            }
        }
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
}

