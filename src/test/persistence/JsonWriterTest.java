package persistence;

import model.AlphaGuess;
import ui.PasswordGame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class JsonWriterTest extends JsonTest {
    // Test case from JSONDemo repo provided on EdX
    @Test
    void testWriterInvalidFile() {
        try {
            PasswordGame pg = new PasswordGame();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNoPastGuesses() {
        try {
            PasswordGame pg = new PasswordGame();
            pg.getPassword().setPasswordContent("abcdef");
            JsonWriter writer = new JsonWriter("./data/testWriterNoPastGuesses.json");
            writer.open();
            writer.write(pg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNoPastGuesses.json");
            pg = reader.read();
            checkPassword("abcdef",0,6,false, pg.getPassword());
            assertEquals(0, pg.getPastGuesses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSomePastGuesses() {
        try {
            PasswordGame pg = new PasswordGame();
            pg.getPassword().setPasswordContent("adefhi");
            pg.makeAGuess("abcdef");
            pg.makeAGuess("afedzy");
            pg.makeAGuess("adefph");
            JsonWriter writer = new JsonWriter("./data/testWriterSomePastGuesses.json");
            writer.open();
            writer.write(pg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSomePastGuesses.json");
            pg = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
