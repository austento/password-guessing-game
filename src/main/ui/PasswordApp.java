package ui;

import model.Guess;
import model.Password;

import java.util.ArrayList;
import java.util.Scanner;

public class PasswordApp {
    private Password password;
    private ArrayList<Guess> pastGuesses;
    private Scanner input;
    private String userInput;

    public PasswordApp() {
        runPassApp();
    }

    private void runPassApp() {
        boolean keepRunning = true;
        userInput = null;
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (keepRunning) {
            displayMainMenu();
            if (input.nextLine().equals("q")) {
                keepRunning = false;
            } else {
                if (input.nextLine().equals("s")) {
                    runNewGame();
                }
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("\ts --> start new game");
        System.out.println("\tq --> quit");
    }

    private void runNewGame() {
        password = new Password();
        pastGuesses = new ArrayList<Guess>();
        System.out.println("\nTry to guess the password");
        while (!password.getIsGuessed()) {
            makeAGuess();
        }
        System.out.println("You have guessed the password!");
    }

    private void makeAGuess() {
        while (!password.getIsGuessed()) {
            System.out.println("Please enter your " + Password.LENGTH + " character guess:");
            userInput = input.nextLine();
            Guess currentGuess = new Guess(userInput);
            currentGuess.compareToPassword(password);
            currentGuess.updateHint();
            pastGuesses.add(currentGuess);
        }
    }
}
