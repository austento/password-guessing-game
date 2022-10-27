package persistence;

import model.AlphaGuess;
import model.AlphaPassword;
import ui.PasswordGame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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
            JsonWriter writer = new JsonWriter("./data/testWriterNoPastGuesses.json");
            writer.open();
            writer.write(pg);
            writer.close();
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
            pg.makeAGuess("adefpq");
            JsonWriter writer = new JsonWriter("./data/testWriterSomePastGuesses.json");
            writer.open();
            writer.write(pg);
            writer.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
