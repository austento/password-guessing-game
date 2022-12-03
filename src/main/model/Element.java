package model;

import java.util.Objects;

// Represents one element at a specific index in a Sequence.
public class Element {
    public enum Colour { GREEN, YELLOW, RED }

    private final Character character;
    private final int location;
    private Colour displayColour;

    //EFFECTS: constructs an element with given String and location
    public Element(String content, int location) {
        this.character = content.charAt(0);
        this.location = location;
    }

    public Character getCharacter() {
        return character;
    }

    public int getLocation() {
        return location;
    }

    public Colour getDisplayColour() {
        return displayColour;
    }

    public void setDisplayColour(Colour colour) {
        displayColour = colour;
        EventLog.getInstance().logEvent(new Event("Element: " + character + location + " changes colour to "
                + colour));
    }

    //EFFECTS: compares two elements for equality
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Element element = (Element) o;
        return location == element.location && Objects.equals(character, element.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, location);
    }

    @Override
    public String toString() {
        return character + "( " + displayColour + " )";
    }
}
