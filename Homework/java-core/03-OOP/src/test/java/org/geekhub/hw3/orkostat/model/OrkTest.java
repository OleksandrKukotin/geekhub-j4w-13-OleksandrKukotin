package org.geekhub.hw3.orkostat.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrkTest {

    Ork testOrk;

    @BeforeEach
    void setUp() {
        testOrk = new Ork(10_001);
    }

    @Test
    void getPrice_ShouldReturnPriceOfLadaVestaSport() {
        assertEquals(10_001, testOrk.getPrice());
    }

    @Test
    void setPrice_ShouldSetOtherPrice() {
        testOrk.setPrice(30_000);
        assertEquals(30_000, testOrk.getPrice());
    }

    @Test
    void scream_ShouldReturnOrkishScream() {
        assertEquals("Aaaaaa!", testOrk.scream());
    }
}
