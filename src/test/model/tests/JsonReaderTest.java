package model.tests;

import model.AnimeEntry;
import model.lists.AnimeList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AnimeList al = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            AnimeList al = reader.read();
            assertEquals("Finished", al.getType());
            assertEquals(0, al.listSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListFl() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListFl.json");
        try {
            AnimeList al = reader.read();
            assertEquals("Finished", al.getType());
            List<AnimeEntry> entries = al.getEntries();
            assertEquals(2, entries.size());
            checkEntry("AOT", AnimeEntry.Status.Finished, "Pog", entries.get(0));
            checkEntry("SAO", AnimeEntry.Status.Finished, "Nice", entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListPl() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListPl.json");
        try {
            AnimeList al = reader.read();
            assertEquals("Planned", al.getType());
            List<AnimeEntry> entries = al.getEntries();
            assertEquals(2, entries.size());
            checkEntry("AOT", AnimeEntry.Status.Planned, "Pog", entries.get(0));
            checkEntry("SAO", AnimeEntry.Status.Planned, "Nice", entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListWl() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListWl.json");
        try {
            AnimeList al = reader.read();
            assertEquals("Watching", al.getType());
            List<AnimeEntry> entries = al.getEntries();
            assertEquals(2, entries.size());
            checkEntry("AOT", AnimeEntry.Status.Watching, "Pog", entries.get(0));
            checkEntry("SAO", AnimeEntry.Status.Watching, "Nice", entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListNone() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListNone.json");
        try {
            AnimeList al = reader.read();
            assertEquals("none", al.getType());
            List<AnimeEntry> entries = al.getEntries();
            assertEquals(2, entries.size());
            checkEntry("AOT", AnimeEntry.Status.Watching, "Pog", entries.get(0));
            checkEntry("SAO", AnimeEntry.Status.Watching, "Nice", entries.get(1));
        } catch (IllegalStateException e) {
            // expected
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
