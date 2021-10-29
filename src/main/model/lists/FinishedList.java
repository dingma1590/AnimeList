package model.lists;

import model.AnimeEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of entries of finished anime
public class FinishedList extends AnimeList {

    public FinishedList() {
        list = new ArrayList<>();
        type = AnimeEntry.Status.Finished.toString();
    }
}
