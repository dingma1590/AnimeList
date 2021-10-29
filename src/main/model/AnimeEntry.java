package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an anime entry with its title, status, and special notes.
public class AnimeEntry implements Writable {
    private String title;

    // Status can be one of Finished, Planned, or Watching
    public enum Status {
        Finished, Planned, Watching
    }

    private Status status;
    private String notes;

    // EFFECTS: AnimeEntry has given title, status, and notes
    public AnimeEntry(String title, Status status, String notes) {
        this.title = title;
        this.status = status;
        this.notes = notes;
    }

    // EFFECTS: returns the title
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns the status
    public Status getStatus() {
        return status;
    }

    // EFFECTS: returns notes
    public String getNotes() {
        return notes;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("status", status);
        json.put("notes", notes);
        return json;
    }
}
