package ui;

import java.util.Scanner;

// Represents the password application - modelled after TellerApp class in Teller application provided
public class PasswordApp {
    private PasswordGame passwordGame;
    private Scanner input;
    private String userInput;

    //EFFECTS: constructs a new password app
    public PasswordApp() {
        runPassApp();
    }

    //MODIFIES: this
    //EFFECTS: runs the password app and processes user input
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
                    passwordGame = new PasswordGame();
                    passwordGame.runNewGame();
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
}
