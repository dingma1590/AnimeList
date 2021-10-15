package model.lists;

import model.AnimeEntry;

import java.util.ArrayList;

public class AnimeList {
    ArrayList<AnimeEntry> list;

    public AnimeList() {
        list = new ArrayList<>();
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
}
