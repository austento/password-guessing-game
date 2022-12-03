package model.persistence;

import model.Event;
import model.EventLog;
import model.Guess;
import model.Password;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
import ui.PasswordGame;

// Represents a reader that reads PasswordGame from JSON data stored in file
// Adapted from given JSONExample file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads PasswordGame from file and returns it
    //         throws IOException if an error occurs reading the data
    public PasswordGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Game progress loaded"));
        return parsePasswordGame(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: creates a new password game that is then loaded with saved data
    private PasswordGame parsePasswordGame(JSONObject jsonObject) {
        PasswordGame pg = new PasswordGame();
        loadPassword(pg, jsonObject);
        addPastGuesses(pg, jsonObject);
        return pg;
    }

    //MODIFIES: Password, PasswordGame
    //EFFECTS: loads previous password from save data into game
    private void loadPassword(PasswordGame pg, JSONObject pgObject) {
        JSONObject password = pgObject.getJSONObject("password");
        boolean guessed = password.getBoolean("guessed");
        String content = password.getString("content");

        Password pw = pg.getPassword();
        pw.setIsGuessed(guessed);
        pw.setPasswordContent(content);
    }


    //MODIFIES: PasswordGame
    //EFFECTS: loads list of past guesses from save data into game
    private void addPastGuesses(PasswordGame pg, JSONObject pgObject) {
        JSONArray jsonArray = pgObject.getJSONArray("pastGuesses");
        for (Object json : jsonArray) {
            JSONObject nextGuess = (JSONObject) json;
            loadGuess(pg, nextGuess);
        }
    }

    //EFFECTS: loads each past guess from save data into game
    private void loadGuess(PasswordGame pg, JSONObject nextGuess) {
        String content = nextGuess.getString("content");

        Guess guess = new Guess(content);
        guess.compareToPassword(pg.getPassword());
        pg.getPastGuesses().add(guess);
    }
}
