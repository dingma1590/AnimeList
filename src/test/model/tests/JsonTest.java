package model.tests;

import model.AnimeEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from CPSC 210 JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// checks an anime entry's info against the json object
public class JsonTest {
    protected void checkEntry(String title, AnimeEntry.Status status, String notes, AnimeEntry entry) {
        assertEquals(title, entry.getTitle());
        assertEquals(status, entry.getStatus());
        assertEquals(notes, entry.getNotes());
    }
}