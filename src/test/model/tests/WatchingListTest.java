package model.tests;

import model.lists.WatchingList;
import org.junit.jupiter.api.BeforeEach;

public class WatchingListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new WatchingList();
    }
}
