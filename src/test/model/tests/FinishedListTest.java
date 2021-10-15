package model.tests;

import model.lists.FinishedList;
import org.junit.jupiter.api.BeforeEach;

public class FinishedListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new FinishedList();
    }
}
