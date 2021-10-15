package model.tests;

import model.AnimeEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for AnimeEntry
public class AnimeEntryTest {
    private AnimeEntry e1;
    private AnimeEntry e2;
    private AnimeEntry e3;

    @BeforeEach
    public void setUp() {
        e1 = new AnimeEntry("Anime1", AnimeEntry.Status.Finished, "Great!");
        e2 = new AnimeEntry("Anime2", AnimeEntry.Status.Watching, "meh");
        e3 = new AnimeEntry("Anime3", AnimeEntry.Status.Planned, "bad");
    }

    @Test
    public void getTitleTest() {
        assertEquals("Anime1", e1.getTitle());
        assertEquals("Anime2", e2.getTitle());
        assertEquals("Anime3", e3.getTitle());
    }

    @Test
    public void getStatusTest() {
        assertEquals(AnimeEntry.Status.Finished, e1.getStatus());
        assertEquals(AnimeEntry.Status.Watching, e2.getStatus());
        assertEquals(AnimeEntry.Status.Planned, e3.getStatus());
    }

    @Test
    public void getNotesTest() {
        assertEquals("Great!", e1.getNotes());
        assertEquals("meh", e2.getNotes());
        assertEquals("bad", e3.getNotes());
    }
}
