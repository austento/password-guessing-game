package ui.gui;

import model.Element;
import model.Guess;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GuessPanel extends JPanel {
    private Guess guess;

    public GuessPanel(Guess guess) {
        this.guess = guess;
        displayGuess();
    }

    private void displayGuess() {
        List<Element> elementList = guess.getContentAsElements();

        for (Element element: elementList) {
            JPanel elementBox = constructElementBox(element);
            this.add(elementBox);
        }
    }

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
