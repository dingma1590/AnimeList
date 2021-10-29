package model.tests;

import model.lists.PlannedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for PlannedList
public class PlannedListTest extends AnimeListTest{

    @BeforeEach
    public void setUp1() {
        testList = new PlannedList();
    }

    @Test
    @Override
    public void getTypeTest() {
        assertEquals("Planned", testList.getType());
    }
}
