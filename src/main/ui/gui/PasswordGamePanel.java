package ui.gui;

import model.Element;
import model.Guess;
import model.Password;
import model.Sequence;
import persistence.JsonWriter;
import ui.PasswordGame;

import javax.swing.*;
import java.awt.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

// Window that allows users to make guesses and view their past guesses
public class PasswordGamePanel extends JPanel {
    Password password;
    private List<Guess> pastGuesses;
    JPanel pastGuessesPanel;
    JPanel mainContentPanel;
    PasswordAppGUI frame;
    CardLayout cardLayout;
    JsonWriter writer;

    //EFFECTS: creates a new PasswordGamePanel with a random password of the type passed in
    public PasswordGamePanel(Password.Type passwordType, PasswordAppGUI frame) {
        this.frame = frame;
        password = new Password(passwordType);
        pastGuesses = new ArrayList<>();
        writer = new JsonWriter("./data/passwordgame.json");

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        pastGuessesPanel = new JPanel();
        pastGuessesPanel.setLayout(new GridLayout(0,1));

        createMainContentPanel();
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password pass) {
        password.setPasswordContent(pass.getPasswordContent());
    }

    //MODIFIES: this
    //EFFECTS: creates the panel that holds the menu and past guesses
    private void createMainContentPanel() {
        mainContentPanel = new JPanel();
        mainContentPanel.add(initGameMenu());
        mainContentPanel.add(initPastGuesses());

        add(mainContentPanel, "main panel");
        cardLayout.show(this,"main panel");
    }

    //EFFECTS: creates the menu
    private JMenuBar initGameMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("game menu");

        menuBar.add(gameMenu);

        JMenuItem makeAGuess = new JMenuItem("make a guess");
        makeAGuess.setActionCommand("guess");
        makeAGuess.addActionListener(frame);
        JMenuItem checkAccuracy = new JMenuItem("check accuracy");
        checkAccuracy.setActionCommand("check");
        checkAccuracy.addActionListener(frame);
        JMenuItem saveProgress = new JMenuItem("save your progress");
        saveProgress.setActionCommand("save");
        saveProgress.addActionListener(frame);
        JMenuItem quitToMainMenu = new JMenuItem("return to main menu");
        quitToMainMenu.setActionCommand("return");
        quitToMainMenu.addActionListener(frame);

        gameMenu.add(makeAGuess);
        gameMenu.add(checkAccuracy);
        gameMenu.add(saveProgress);
        gameMenu.add(quitToMainMenu);

        return menuBar;
    }

    //EFFECTS: creates pastGuesses
    private JScrollPane initPastGuesses() {
        JScrollPane pastGuessesScrollPane = new JScrollPane(pastGuessesPanel);
        return pastGuessesScrollPane;
    }

    //MODIFIES: this
    //EFFECTS: adds a guess to the display of past guesses
    public void addGuess(Guess guess) {
        GuessDisplayPanel guessDisplay = new GuessDisplayPanel(guess);
        mainContentPanel.add(guessDisplay);
        pastGuesses.add(guess);
    }

    //EFFECTS: calculates a score for the user
    public double calculateScore() {
        double score = 100;
        score -= pastGuesses.size() * 1.6;
        return score;
    }

    //EFFECTS: calculates the user's accuracy of guesses
    public double calculateAccuracy() {
        int correctChar = 0;
        int correctElements = 0;
        for (Guess guess: pastGuesses) {
            List<Element> elementList = guess.getContentAsElements();
            for (Element element: elementList) {
                if (element.getDisplayColour().equals(Element.Colour.GREEN)) {
                    correctElements++;
                }
                if (element.getDisplayColour().equals(Element.Colour.YELLOW)) {
                    correctChar++;
                }
            }
        }
        int allChar = pastGuesses.size() * Sequence.LENGTH;
        double yellow = (double) correctChar / (double) allChar;
        double green = (double) correctElements / (double) allChar;
        return (0.2 * yellow) + (0.8 * green);
    }

    //EFFECTS: saves the user's progress
    public void savePasswordGame() throws FileNotFoundException {
        PasswordGame pg = new PasswordGame();
        pg.setPassword(password);
        pg.setPastGuesses(pastGuesses);

        writer.open();
        writer.write(pg);
        writer.close();
    }
}
