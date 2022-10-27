package persistence;

import model.AlphaGuess;
import org.junit.jupiter.api.Test;
import ui.PasswordGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/NonExistentFile.json");
        try {
            PasswordGame pg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoPastGuesses() {
        JsonReader reader = new JsonReader("./data/testWriterNoPastGuesses");
        try {
            PasswordGame pg = reader.read();
            assertEquals(0, pg.getPastGuesses().size());
            checkPassword("adefhi",4,2, false, pg.getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSomePastGuesses() {
        JsonReader reader = new JsonReader("./data/testWriterSomePastGuesses.json");
        try {
            PasswordGame pg = reader.read();
            checkPassword("adefhi",4, 2, false, pg.getPassword());
            ArrayList<AlphaGuess> pastGuesses = pg.getPastGuesses();
            assertEquals(3, pastGuesses.size());
            checkGuess("abcdef",4,1,
                    "4 of those characters are in the password, and 1 are in their correct position",
                    pastGuesses.get(0));
            checkGuess("afedzy",4, 2,
                    "4 of those characters are in the password, and 2 are in their correct position",
                    pastGuesses.get(1));
            checkGuess("adefph", 5, 4,
                    "5 of those characters are in the password, and 4 are in their correct position",
                    pastGuesses.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
