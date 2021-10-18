package model.tests;

import model.lists.FinishedList;
import org.junit.jupiter.api.BeforeEach;

// Unit tests for FinishedList
public class FinishedListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new FinishedList();
    }
}
