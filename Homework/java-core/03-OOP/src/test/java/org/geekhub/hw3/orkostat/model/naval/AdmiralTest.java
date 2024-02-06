package org.geekhub.hw3.orkostat.model.naval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdmiralTest {

    Admiral testAdmiral;

    @BeforeEach
    void setUp() {
        testAdmiral = new Admiral(10_000, 500);
    }
    @Test
    void getNavigationHours() {
        assertEquals(500, testAdmiral.getNavigationHours());
    }

    @Test
    void setNavigationHours() {
        testAdmiral.setNavigationHours(600);
        assertEquals(600, testAdmiral.getNavigationHours());
    }
}
