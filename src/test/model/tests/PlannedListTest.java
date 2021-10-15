package model.tests;

import model.lists.PlannedList;
import org.junit.jupiter.api.BeforeEach;

public class PlannedListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new PlannedList();
    }
}
