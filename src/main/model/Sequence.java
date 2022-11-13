package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Sequence {
    public static final int LENGTH = 6;

    protected String content;
    protected List<Element> contentAsElements;

    public Sequence(String input) {
        content = input;
    }

    public List<Character> contentToList() {
        List<Character> list = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            char character = content.charAt(i);
            list.add(character);
        }

        return list;
    }

    public List<Element> contentToElementList() {
        List<Element> list = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            Element element = new Element(content.substring(i,i + 1), i);
            list.add(element);
        }

        return list;
    }

}
