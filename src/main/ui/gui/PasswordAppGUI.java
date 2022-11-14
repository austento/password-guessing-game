package ui.gui;

import model.Guess;
import model.Password;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;

public class PasswordAppGUI extends JFrame {
    private JsonReader jsonReader;

    //EFFECTS: constructs and runs a new PasswordGame
    public PasswordAppGUI() {
        super("Password Game");
        newGameWindow();
    }

    //MODIFIES: this
    //EFFECTS: constructs a new game window
    public void newGameWindow() {
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        displayMainMenu();
        displayTitle();
//        Guess guess = new Guess("abcdef");
//        Password password = new Password(Password.Type.ALPHABETIC);
//        password.setPasswordContent("acdhif");
//        guess.compareToPassword(password);
//        this.add(BorderLayout.SOUTH,new GuessPanel(guess));
//        this.revalidate();
    }

    private void displayMainMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.X_AXIS));

        JButton startNewGameButton = new JButton("start new game");
        JButton loadGameButton = new JButton("load game");

        mainMenu.add(startNewGameButton);
        mainMenu.add(loadGameButton);

        this.add(BorderLayout.WEST, mainMenu);
        revalidate();
    }

    private void displayTitle() {
        JPanel title = new JPanel();

        JLabel titleContent = new JLabel("try to guess the password!");

        title.add(titleContent);
        this.add(BorderLayout.NORTH, title);
    }


}
