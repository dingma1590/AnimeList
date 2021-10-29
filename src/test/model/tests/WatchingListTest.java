package model.tests;

import model.lists.WatchingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for WatchingList
public class WatchingListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new WatchingList();
    }

    @Test
    @Override
    public void getTypeTest() {
        assertEquals("Watching", testList.getType());
    }
}
