package ui;

import model.persistence.JsonReader;

import java.io.IOException;
import java.util.Scanner;

// Represents the password application - modelled after TellerApp class in Teller application provided
public class PasswordApp {
    private PasswordGame passwordGame;
    private JsonReader jsonReader;

    //EFFECTS: constructs a new password app
    public PasswordApp() {
        runPassApp();
    }

    //MODIFIES: this
    //EFFECTS: runs the password app and processes user input
    private void runPassApp() {
        boolean keepRunning = true;
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        String userInput;
        jsonReader = new JsonReader(PasswordGame.JSON_STORE);

        while (keepRunning) {
            displayMainMenu();
            userInput = input.next();
            switch (userInput) {
                case "q":
                    keepRunning = false;
                    break;
                case "s":
                    passwordGame = new PasswordGame();
                    passwordGame.choosePasswordType();
                    passwordGame.runNewGame();
                    break;
                case "l":
                    loadPasswordGame();
                    passwordGame.runNewGame();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: loads password game from file
    public void loadPasswordGame() {
        try {
            passwordGame = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to load game!");
        }
    }

    //EFFECTS: displays the app's main menu
    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("\ts --> start new game");
        System.out.println("\tl --> load game");
        System.out.println("\tq --> quit");
    }
}
