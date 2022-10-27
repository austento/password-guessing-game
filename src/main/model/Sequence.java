package model;

import java.util.ArrayList;

public abstract class Sequence {
    public static final int LENGTH = 6;

    protected ArrayList<Character> content;

    public Sequence() {
        content = new ArrayList<>();
    }

    public String contentToString() {
        StringBuilder result = new StringBuilder();
        for (Character c: content) {
            result.append(c);
        }
        return result.toString();
    }
}
