package ui.gui;

import model.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// a pop-up that allows the user to choose what kind of password they want to generate
// code adapted from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
public class PasswordTypeDialog extends JPanel {
    private ButtonGroup group;
    private PasswordAppGUI frame;
    final String numericPasswordOption = "numeric";
    final String alphabeticPasswordOption = "alphabetic";
    final String alphanumPasswordOption = "alphanum";
    final String asciiPasswordOption = "ascii";

    //EFFECTS: creates a new PasswordTypeDialog in the main window
    public PasswordTypeDialog(PasswordAppGUI frame) {
        super(new BorderLayout());
        this.frame = frame;
        createDialogBox();
        frame.add(this);
    }

    //EFFECTS: creates the panel that holds all the components of the dialog
    private JPanel createPanel(JRadioButton[] radioButtons, JButton showButton) {
        JPanel box = new JPanel();
        JLabel label = new JLabel("please choose the kind of password you would like to create:");

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);

        for (JRadioButton radioButton : radioButtons) {
            box.add(radioButton);
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(box, BorderLayout.PAGE_START);
        panel.add(showButton, BorderLayout.PAGE_END);
        return panel;
    }

    //MODIFIES: this
    //EFFECTS: creates the options the user sees in the dialog
    private void createDialogBox() {
        final int numButtons = 4;
        JRadioButton[] passwordOptionTypes = new JRadioButton[numButtons];
        group = new ButtonGroup();

        passwordOptionTypes[0] = new JRadioButton("numeric (could only contain numbers 1-9)");
        passwordOptionTypes[0].setActionCommand(numericPasswordOption);

        passwordOptionTypes[1] = new JRadioButton("alphabetical (could only contain the 26 letters in the alphabet");
        passwordOptionTypes[1].setActionCommand(alphabeticPasswordOption);

        passwordOptionTypes[2] =
                new JRadioButton("alphabetical + numeric (could contain numbers 1-9 and 26 letters in the alphabet");
        passwordOptionTypes[2].setActionCommand(alphanumPasswordOption);

        passwordOptionTypes[3] = new JRadioButton("ascii (could contain any ascii character - i.e. $, %, etc)");
        passwordOptionTypes[3].setActionCommand(asciiPasswordOption);

        for (int i = 0; i < numButtons; i++) {
            group.add(passwordOptionTypes[i]);
        }
        passwordOptionTypes[0].setSelected(true);

        JButton confirm = new JButton("confirm");

        this.add(createPanel(passwordOptionTypes,confirm), BorderLayout.CENTER);
        addButtonFunction(confirm);
    }

    //MODIFIES: this
    //          PasswordAppGUI
    //          PasswordGamePanel
    //EFFECTS: handles the events associated with each radio button
    public void addButtonFunction(JButton confirmButton) {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                if (command.equals(numericPasswordOption)) {
                    frame.currentGamePanel = new PasswordGamePanel(Password.Type.NUMERIC,frame);
                    frame.showPanel(frame.currentGamePanel,"game panel");
                }

                if (command.equals(alphabeticPasswordOption)) {
                    frame.currentGamePanel = new PasswordGamePanel(Password.Type.ALPHABETIC,frame);
                    frame.showPanel(frame.currentGamePanel,"game panel");
                }

                if (command.equals(alphanumPasswordOption)) {
                    frame.currentGamePanel = new PasswordGamePanel(Password.Type.NUMALPHA,frame);
                    frame.showPanel(frame.currentGamePanel,"game panel");
                }

                if (command.equals(asciiPasswordOption)) {
                    frame.currentGamePanel = new PasswordGamePanel(Password.Type.ASCII,frame);
                    frame.showPanel(frame.currentGamePanel,"game panel");
                }
            }
        });
    }
}
