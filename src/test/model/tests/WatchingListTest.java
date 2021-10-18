package model.tests;

import model.lists.WatchingList;
import org.junit.jupiter.api.BeforeEach;

// Unit tests for WatchingList
public class WatchingListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new WatchingList();
    }
}
