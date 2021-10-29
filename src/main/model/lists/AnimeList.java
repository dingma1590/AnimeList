package model.lists;

import model.AnimeEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of anime entries
public class AnimeList implements Writable {
    ArrayList<AnimeEntry> list;
    String type;

    // constructor
    public AnimeList() {
        list = new ArrayList<>();
        type = "none";
    }

    // MODIFIES: this
    // EFFECTS: returns true and inserts an anime entry into the list if it's not there already, false otherwise
    public boolean addEntry(AnimeEntry ae) {
        for (AnimeEntry next: list) {
            if (ae.getTitle().equalsIgnoreCase(next.getTitle())) {
                return false;
            }
        }
        list.add(ae);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: returns true and removes an anime entry from the list if it's there, false otherwise
    public boolean removeEntry(String t) {
        for (AnimeEntry next: list) {
            if (t.equalsIgnoreCase(next.getTitle())) {
                list.remove(next);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of entries in list
    public int listSize() {
        return list.size();
    }

    // EFFECTS: returns a list of titles
    public ArrayList<AnimeEntry> getEntries() {
        ArrayList<AnimeEntry> entries = new ArrayList<>();
        for (AnimeEntry next: list) {
            entries.add(next);
        }
        return entries;
    }

    // EFFECTS: returns true if entry exists in list
    public boolean hasEntry(String t) {
        for (AnimeEntry next: list) {
            if (t.equalsIgnoreCase(next.getTitle())) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: Entry of given string to exist in list
    // EFFECTS: returns the entry of given title
    public AnimeEntry getEntry(String t) {
        for (AnimeEntry next: list) {
            if (t.equalsIgnoreCase(next.getTitle())) {
                return next;
            }
        }
        return null;
    }

    // EFFECTS: return the list's type
    public String getType() {
        return type;
    }

    @Override
    // EFFECTS creates a new json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("list", listToJson());
        return json;
    }

    // EFFECTS: returns anime entries in this list as a JSON array
    private JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();

        for (AnimeEntry next : list) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }
}
