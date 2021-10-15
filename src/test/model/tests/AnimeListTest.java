package model.tests;

import model.AnimeEntry;
import model.lists.AnimeList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for AnimeList
public class AnimeListTest {
    AnimeList testList;
    private AnimeEntry e1;
    private AnimeEntry e2;
    private AnimeEntry e3;

    @BeforeEach
    public void setUp1() {
        testList = new AnimeList();
    }

    @BeforeEach
    public void setUp2() {
        e1 = new AnimeEntry("Anime1", AnimeEntry.Status.Finished, "Great!");
        e2 = new AnimeEntry("Anime2", AnimeEntry.Status.Watching, "meh");
        e3 = new AnimeEntry("Anime3", AnimeEntry.Status.Planned, "bad");
    }

    @Test
    public void addEntryTest() {
        assertTrue(testList.addEntry(e1));
        assertEquals(1, testList.listSize());

        assertTrue(testList.addEntry(e2));
        assertEquals(2, testList.listSize());

        assertFalse(testList.addEntry(e2));
        assertEquals(2, testList.listSize());
    }

    @Test
    public void removeEntryTest() {
        testList.addEntry(e1);
        testList.addEntry(e2);

        assertTrue(testList.removeEntry("Anime1"));
        assertEquals(1, testList.listSize());
        assertFalse(testList.removeEntry("Anime3"));
        assertEquals(1, testList.listSize());
    }

    @Test
    public void listSizeTest() {
        testList.addEntry(e1);
        testList.addEntry(e2);
        assertEquals(2, testList.listSize());

        testList.addEntry(e3);
        assertEquals(3, testList.listSize());
    }

    @Test
    public void getTitlesTest() {
        testList.addEntry(e1);
        testList.addEntry(e2);
        assertEquals(2, testList.getTitles().size());
    }

    @Test
    public void hasEntryTest() {
        testList.addEntry(e1);
        testList.addEntry(e2);
        assertTrue(testList.hasEntry("Anime1"));
        assertFalse(testList.hasEntry("Anime3"));
    }

    @Test
    public void getEntryTest() {
        testList.addEntry(e1);
        testList.addEntry(e2);
        assertEquals(e1, testList.getEntry("Anime1"));
        assertEquals(e2, testList.getEntry("Anime2"));
        assertNull(testList.getEntry("Anime3"));
    }
}