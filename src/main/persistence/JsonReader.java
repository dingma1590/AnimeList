package persistence;

import model.AnimeEntry;
import model.lists.AnimeList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.lists.FinishedList;
import model.lists.PlannedList;
import model.lists.WatchingList;
import org.json.*;

// Represents a reader that reads anime lists from JSON data stored in file
// This class references code from CPSC 210 JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads anime list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AnimeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAnimeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses anime list from JSON object and returns it
    private AnimeList parseAnimeList(JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        AnimeList al;
        switch (type) {
            case "Finished":
                al = new FinishedList();
                break;
            case "Planned":
                al = new PlannedList();
                break;
            case "Watching":
                al = new WatchingList();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        addEntries(al, jsonObject);
        return al;
    }

    // MODIFIES: al
    // EFFECTS: parses entries from JSON object and adds them to anime list
    private void addEntries(AnimeList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(al, nextEntry);
        }
    }

    // MODIFIES: al
    // EFFECTS: parses anime entry from JSON object and adds it to anime list
    private void addEntry(AnimeList al, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        AnimeEntry.Status status = AnimeEntry.Status.valueOf(jsonObject.getString("status"));
        String notes = jsonObject.getString("notes");
        AnimeEntry entry = new AnimeEntry(title, status, notes);
        al.addEntry(entry);
    }
}
