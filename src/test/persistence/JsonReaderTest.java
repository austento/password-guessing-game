package persistence;

import model.Event;
import model.EventLog;
import model.Guess;
import model.persistence.JsonReader;
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
        JsonReader reader = new JsonReader("./data/testWriterNoPastGuesses.json");
        try {
            PasswordGame pg = reader.read();
            assertEquals(0, pg.getPastGuesses().size());
            checkPassword("abcdef",false, pg.getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSomePastGuesses() {
        JsonReader reader = new JsonReader("./data/testWriterSomePastGuesses.json");
        try {
            PasswordGame pg = reader.read();
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
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadEventAddToLog() {
        JsonReader reader = new JsonReader("./data/testWriterSomePastGuesses.json");
        List<String> events = new ArrayList<>();
        EventLog.getInstance().clear();

        try {
            reader.read();
        } catch (IOException e) {
            fail("Couldn't read file");
        }

        EventLog el = EventLog.getInstance();
        for (Event event: el) {
            events.add(event.getDescription());
        }

        assertTrue(events.contains("Game progress loaded"));
    }


}
