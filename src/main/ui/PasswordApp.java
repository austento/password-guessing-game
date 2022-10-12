package ui;

import model.Guess;
import model.Password;

import java.util.ArrayList;
import java.util.Scanner;

// Represents the password application
public class PasswordApp {
    private Password password;
    private ArrayList<Guess> pastGuesses;
    private Scanner input;
    private String userInput;

    public PasswordApp() {
        runPassApp();
    }

    // MODIFIES: this
    // EFFECTS: runs the password app and processes user input
    private void runPassApp() {
        boolean keepRunning = true;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        userInput = null;

        while (keepRunning) {
            displayMainMenu();
            userInput = input.next();
            if (userInput.equals("q")) {
                keepRunning = false;
            } else {
                if (userInput.equals("s")) {
                    runNewGame();
                }
            }
        }
    }

    //EFFECTS: displays the app's main menu
    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("\ts --> start new game");
        System.out.println("\tq --> quit");
    }

    // EFFECTS: displays the in-game menu
    private void displayGameMenu() {
        System.out.println("\nTry to guess the password!");
        System.out.println("\tg --> make a guess");
        System.out.println("\td --> show past guesses");
        System.out.println("\tm --> give up and return to main menu");
    }

    // MODIFIES: this
    // EFFECTS: runs a new game
    //          creates a new password for the user to guess
    //          processes user input related to the game
    private void runNewGame() {
        password = new Password();
        pastGuesses = new ArrayList<>();
        userInput = null;
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

    // MODIFIES: this
    // EFFECTS: creates a new guess using the user's input
    //          compares guess to password
    //          stops game if password is guessed
    private void makeAGuess() {
        System.out.println("Please enter your " + Password.LENGTH + " character guess:");
        userInput = input.next();
        if (userInput.length() < Password.LENGTH) {
            System.out.println("Your guess has to be " + Password.LENGTH + " characters long!");
        } else {
            Guess currentGuess = new Guess(userInput);
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

    // EFFECTS: displays each of the past guesses a user has made
    //          if no guesses exist, tells the user they have not made any guesses
    private void displayPastGuesses() {
        if (pastGuesses.size() == 0) {
            System.out.println("You have not made any guesses!");
        } else {
            System.out.println("These are your past guesses:");
            for (Guess pastGuess : pastGuesses) {
                System.out.println(pastGuess);
            }
        }
    }

    // EFFECTS: calculates and displays a score
    private void displayScore() {
        double score = 100;
        for (Guess guess: pastGuesses) {
            if (pastGuesses.indexOf(guess) <= 10) {
                score -= (double) (1 / 100) * (pastGuesses.indexOf(guess));
            } // stub
        }
        System.out.println("Your score is: " + score);
    }
}
