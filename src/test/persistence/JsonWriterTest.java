package persistence;

import model.Event;
import model.EventLog;
import model.Guess;
import ui.PasswordGame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            checkPassword("abcdef",false, pg.getPassword());
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
            checkPassword("adefhi",false, pg.getPassword());
            List<Guess> pastGuesses = pg.getPastGuesses();
            assertEquals(3, pastGuesses.size());
            checkGuess("abcdef",
                    "a( GREEN )b( RED )c( RED )d( YELLOW )e( YELLOW )f( YELLOW )",
                    pastGuesses.get(0));
            checkGuess("afedzy",
                    "a( GREEN )f( YELLOW )e( GREEN )d( YELLOW )z( RED )y( RED )",
                    pastGuesses.get(1));
            checkGuess("adefph",
                    "a( GREEN )d( GREEN )e( GREEN )f( GREEN )p( RED )h( YELLOW )",
                    pastGuesses.get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriteEventAddToLog() {
        JsonWriter writer = new JsonWriter("./data/testWriterSomePastGuesses.json");
        List<String> events = new ArrayList<>();
        EventLog.getInstance().clear();

        try {
            writer.open();
            writer.write(new PasswordGame());
            writer.close();
        } catch (IOException e) {
            fail("Couldn't write file");
        }

        EventLog el = EventLog.getInstance();
        for (Event event: el) {
            events.add(event.getDescription());
        }

        assertTrue(events.contains("Game progress saved"));
    }
}
