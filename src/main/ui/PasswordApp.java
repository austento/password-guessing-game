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

    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("\ts --> start new game");
        System.out.println("\tq --> quit");
    }

    private void displayGameMenu() {
        System.out.println("\nTry to guess the password!");
        System.out.println("\tg --> make a guess");
        System.out.println("\td --> show past guesses");
        System.out.println("\tm --> main menu");
    }

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
                    displayMainMenu();
                    break;
            }
        }
    }

    private void makeAGuess() {
        System.out.println("Please enter your " + Password.LENGTH + " character guess:");
        userInput = input.next();
        Guess currentGuess = new Guess(userInput);
        currentGuess.compareToPassword(password);
        if (!password.getIsGuessed()) {
            currentGuess.updateHint();
            pastGuesses.add(currentGuess);
            System.out.println(currentGuess);
        } else {
            System.out.println("You have guessed the password!");
        }
    }

    private void displayPastGuesses() {
        if (pastGuesses.size() == 0) {
            System.out.println("You have not made any guesses!");
        } else {
            for (Guess pastGuess : pastGuesses) {
                System.out.println(pastGuess);
            }
        }
    }
}
