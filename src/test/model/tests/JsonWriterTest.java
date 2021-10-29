package model.tests;

import model.AnimeEntry;
import model.lists.AnimeList;
import model.lists.FinishedList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            AnimeList al = new AnimeList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            AnimeList al = new FinishedList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            al = reader.read();
            assertEquals("Finished", al.getType());
            assertEquals(0, al.listSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralList() {
        try {
            AnimeList al = new FinishedList();
            al.addEntry(new AnimeEntry("AOT", AnimeEntry.Status.Finished, "Pog"));
            al.addEntry(new AnimeEntry("SAO", AnimeEntry.Status.Finished, "Nice"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralList.json");
            al = reader.read();
            assertEquals("Finished", al.getType());
            List<AnimeEntry> entries = al.getEntries();
            assertEquals(2, entries.size());
            checkEntry("AOT", AnimeEntry.Status.Finished, "Pog", entries.get(0));
            checkEntry("SAO", AnimeEntry.Status.Finished, "Nice", entries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
