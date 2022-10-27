package persistence;

import model.AlphaGuess;
import model.Guess;
import model.Password;
import ui.PasswordApp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;
import ui.PasswordGame;

// Represents a reader that reads PasswordGame from JSON data stored in file
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

    private PasswordGame parsePasswordGame(JSONObject jsonObject) {
        PasswordGame pg = new PasswordGame();
        loadPassword(pg, jsonObject);
        addPastGuesses(pg, jsonObject);
        return pg;
    }

    private void loadPassword(PasswordGame pg, JSONObject pgObject) {
        JSONObject password = pgObject.getJSONObject("password");
        int charGuessed = password.getInt("charGuessed");
        int charNotGuessed = password.getInt("charNotGuessed");
        boolean isGuessed = password.getBoolean("isGuessed");
        String content = password.getString("content");

        Password pw = pg.getPassword();
        pw.setCharGuessed(charGuessed);
        pw.setCharNotGuessed(charNotGuessed);
        pw.setIsGuessed(isGuessed);
        pw.setPasswordContent(content);
    }

    private void addPastGuesses(PasswordGame pg, JSONObject pgObject) {
        JSONArray jsonArray = pgObject.getJSONArray("pastGuesses");
        for (Object json : jsonArray) {
            JSONObject nextGuess = (JSONObject) json;
            loadGuess(pg, nextGuess);
        }
    }

    private void loadGuess(PasswordGame pg, JSONObject nextGuess) {
        int numCharCorrect = nextGuess.getInt("numCharCorrect");
        int numCharCorrectPos = nextGuess.getInt("numCharCorrectPos");
        String content = nextGuess.getString("content");

        AlphaGuess guess = new AlphaGuess(content);
        guess.setNumCharCorrect(numCharCorrect);
        guess.setNumCharCorrectPos(numCharCorrectPos);
        guess.updateHint();
        pg.getPastGuesses().add(guess);
    }
}
