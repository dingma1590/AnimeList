package model.lists;

import model.AnimeEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of entries of anime currently watching
public class WatchingList extends AnimeList {

    // constructor
    public WatchingList() {
        list = new ArrayList<>();
        type = AnimeEntry.Status.Watching.toString();
    }
}
