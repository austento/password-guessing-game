package ui.gui;

import model.Element;
import model.Guess;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Represents a panel that creates a visual representation of a guess made by the user
public class GuessDisplayPanel extends JPanel {
    private Guess guess;

    //EFFECTS: constructs a new GuessDisplayPanel that visualizes the Guess passed as param
    public GuessDisplayPanel(Guess guess) {
        this.guess = guess;
        displayGuessPanel();
    }

    //EFFECTS: creates display of the Guess
    private void displayGuessPanel() {
        List<Element> elementList = guess.getContentAsElements();

        for (Element element: elementList) {
            JPanel elementBox = constructElementBox(element);
            this.add(elementBox);
        }
    }

    //EFFECTS: creates display of each Element in Guess
    private JPanel constructElementBox(Element element) {
        char elementCharacter = element.getCharacter();
        Element.Colour displayColour = element.getDisplayColour();

        JPanel elementBox = new JPanel();

        switch (displayColour) {
            case RED:
                elementBox.setBackground(Color.red);
                break;
            case YELLOW:
                elementBox.setBackground(Color.yellow);
                break;
            case GREEN:
                elementBox.setBackground(Color.green);
                break;
            default:
                elementBox.setBackground(Color.darkGray);
                break;
        }

        JLabel character = new JLabel(String.valueOf(elementCharacter));
        elementBox.add(character);

        return elementBox;
    }


}
