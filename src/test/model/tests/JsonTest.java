package model.tests;

import model.AnimeEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(String title, AnimeEntry.Status status, String notes, AnimeEntry entry) {
        assertEquals(title, entry.getTitle());
        assertEquals(status, entry.getStatus());
        assertEquals(notes, entry.getNotes());
    }
}