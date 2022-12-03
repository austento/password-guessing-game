package ui.gui;

import model.Guess;
import model.Password;
import model.Sequence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

// A panel with text entry for the user to enter their guess
// code from: https://docs.oracle.com/javase/tutorial/uiswing/components/passwordfield.html
public class GuessEntryPanel extends JPanel implements ActionListener {
    private static final String OK = "ok";
    private static final String HELP = "help";

    private PasswordGamePanel panel;
    private PasswordAppGUI frame;
    private JPasswordField guessEntry;
    private Password password;

    //EFFECTS: creates a new GuessEntryPanel for the user
    public GuessEntryPanel(PasswordGamePanel panel, PasswordAppGUI frame, Password password) {
        this.panel = panel;
        this.frame = frame;
        this.password = password;
        guessEntry = new JPasswordField(Sequence.LENGTH);
        guessEntry.setActionCommand(OK);
        guessEntry.addActionListener(this);

        JLabel label = new JLabel("Please enter the password: ");
        label.setLabelFor(guessEntry);

        JComponent buttonPane = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label);
        textPane.add(guessEntry);

        add(textPane);
        add(buttonPane);

    }

    //EFFECTS: creates a panel of buttons
    private JComponent createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(0,1));

        JButton okButton = new JButton(OK);
        okButton.setActionCommand(OK);
        okButton.addActionListener(this);

        JButton helpButton = new JButton(HELP);
        helpButton.setActionCommand(HELP);
        helpButton.addActionListener(this);

        JButton cancelButton = new JButton("cancel");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);

        buttonPanel.add(okButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    //MODIFIES: PasswordAppGUI
    //EFFECTS: handles action events for each button in the button panel
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals(OK)) {
            String userInput = valueOf(guessEntry.getPassword());
            if (userInput.length() == Sequence.LENGTH) {
                evaluateGuess(userInput);
                frame.cardLayout.show(frame.mainContentPanel, "game panel");
            } else {
                JOptionPane.showMessageDialog(panel,"the password must be " + Sequence.LENGTH
                        + " characters long - please try again.","error message",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (command.equals(HELP)) {
            JOptionPane.showMessageDialog(panel,"enter your 6 character guess - "
                    + "check your previous guesses if you need a hint"
                    + "\nor press cancel to go back to the game menu","help message",JOptionPane.INFORMATION_MESSAGE);
        }
        if (command.equals("cancel")) {
            frame.cardLayout.show(frame.mainContentPanel, "game panel");
        }
    }

    //MODIFIES: PasswordGamePanel
    //EFFECTS: compares the guess entered to the password and adds + displays the guess to the user's pastGuesses
    private void evaluateGuess(String guessContent) {
        Guess guess = new Guess(guessContent);
        guess.compareToPassword(password);
        if (password.getIsGuessed()) {
            ImageIcon unlockImage = new ImageIcon("data/unlock_animaiton.gif");
            JOptionPane.showMessageDialog(panel,"you guessed the password!" + "\nscore: " + panel.calculateScore(),
                    "success!", JOptionPane.INFORMATION_MESSAGE,unlockImage);
        } else {
            panel.addGuess(guess);
        }
    }
}
