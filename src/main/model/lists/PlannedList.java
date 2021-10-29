package model.lists;

import model.AnimeEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of entries of planned to watch anime
public class PlannedList extends AnimeList {

    public PlannedList() {
        list = new ArrayList<>();
        type = AnimeEntry.Status.Planned.toString();
    }
}
