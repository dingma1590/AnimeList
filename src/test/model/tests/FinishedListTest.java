package model.tests;

import model.lists.FinishedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for FinishedList
public class FinishedListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new FinishedList();
    }

    @Test
    @Override
    public void getTypeTest() {
        assertEquals("Finished", testList.getType());
    }
}
