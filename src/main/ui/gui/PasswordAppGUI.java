package ui.gui;

import model.Guess;
import model.Password;
import persistence.JsonReader;
import ui.PasswordGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Main window of game where users can start a new game or load previous progress
public class PasswordAppGUI extends JFrame implements ActionListener {
    JsonReader reader;
    JPanel mainContentPanel;
    PasswordGamePanel currentGamePanel;
    CardLayout cardLayout;

    //EFFECTS: constructs and runs a new PasswordApp
    public PasswordAppGUI() {
        super("Password Game");
        newGameWindow();
        reader = new JsonReader("./data/passwordgame.json");
    }

    //MODIFIES: this
    //EFFECTS: constructs a new game window
    public void newGameWindow() {
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        displayMainMenu();
        displayTitle();
        revalidate();

        add(BorderLayout.CENTER,mainContentPanel);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates the main menu
    private void displayMainMenu() {
        JPanel mainMenu = new JPanel();

        JButton startNewGameButton = new JButton("start new game");
        JButton loadGameButton = new JButton("load game");

        mainMenu.add(startNewGameButton);
        mainMenu.add(loadGameButton);

        mainContentPanel.add(mainMenu,"main menu");
        cardLayout.show(mainContentPanel,"main menu");

        linkButtons(startNewGameButton, loadGameButton);
        revalidate();
    }

    //MODIFIES: this
    //EFFECTS: adds the title to the game window
    private void displayTitle() {
        JPanel title = new JPanel();

        JLabel titleContent = new JLabel("try to guess the password!");

        title.add(titleContent);
        this.add(BorderLayout.NORTH, title);
    }

    //MODIFIES: this
    //EFFECTS: shows the panel passed as param
    public void showPanel(JPanel panel, String name) {
        mainContentPanel.add(panel,name);
        cardLayout.show(mainContentPanel,name);
    }

    //EFFECTS: sets up actions for buttons in main menu
    private void linkButtons(JButton startNewGameButton, JButton loadGameButton) {
        startNewGameButton.setActionCommand("new");
        startNewGameButton.addActionListener(this);

        loadGameButton.setActionCommand("load");
        loadGameButton.addActionListener(this);
    }


    //MODIFIES: this
    //          PasswordGamePanel
    //          GuessEntryPanel
    //EFFECTS: handles events associated with buttons on main menu and in PasswordGamePanel
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("new".equals(command)) {
            newGame();
        }
        if ("load".equals(command)) {
            loadProgress();
        }
        if ("guess".equals(command)) {
            newGuess();
        }
        if ("check".equals(command)) {
            checkAccuracy();
        }
        if ("save".equals(command)) {
            saveProgress();
        }
        if ("return".equals(command)) {
            cardLayout.show(mainContentPanel,"main menu");
        }
    }

    private void newGame() {
        JPanel passwordTypeDialog = new PasswordTypeDialog(this);
        showPanel(passwordTypeDialog,"password options");
    }

    private void newGuess() {
        GuessEntryPanel guessEntryPanel = new GuessEntryPanel(currentGamePanel,
                this,currentGamePanel.getPassword());
        showPanel(guessEntryPanel,"guess entry");
    }

    private void checkAccuracy() {
        JOptionPane.showMessageDialog(currentGamePanel,"your current accuracy is: "
                + currentGamePanel.calculateAccuracy() * 100 + "%");
    }

    //MODIFIES: this
    //          PasswordGamePanel
    //EFFECTS: loads previously saved progress
    private void loadProgress() {
        try {
            PasswordGame pg = reader.read();
            Password pass = pg.getPassword();
            List<Guess> pastGuesses = pg.getPastGuesses();
            currentGamePanel = new PasswordGamePanel(Password.Type.NUMERIC, this);
            currentGamePanel.setPassword(pass);
            for (Guess guess : pastGuesses) {
                currentGamePanel.addGuess(guess);
            }
            showPanel(currentGamePanel, "game panel");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainContentPanel,"unable to load progress...","error message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: saves user's progress
    private void saveProgress() {
        try {
            currentGamePanel.savePasswordGame();
            JOptionPane.showMessageDialog(currentGamePanel,"progress saved successfully!");

        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(currentGamePanel,"unable to save progress...","error message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
