package org.geekhub.hw3.orkostat.model.naval;

import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.SimpleCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShipTest {

    Admiral testAdmiral;
    Ship testShip;

    @BeforeEach()
    void setUp() {
        testAdmiral = new Admiral(3000);
        testShip = new Ship(testAdmiral, 2);
        Ship testAdvancedShip = new Ship(testAdmiral, 3, new Ork(50), new Ork(300));
    }

    @Test
    void getEquipage_shouldReturnInstanceOfSimpleCollection() {
        assertInstanceOf(SimpleCollection.class, testShip.getEquipage());
    }

    @Test
    void getPrice_shouldReturnPriceOfTheShip() {
        assertEquals(10_000_000, testShip.getPrice());
    }

    @Test
    void shoot_shouldReturnBoom() {
        assertEquals("BOOM!", testShip.shoot());
    }

    @Test
    void putOrc_WherePuttingNewOrk_WhenTheCapacityIsNotExhausted_ShouldReturnTrue() {
        assertTrue(testShip.putOrk(new Ork()));
    }

    @Test
    void putOrc_WherePuttingNewOrk_WhenTheCapacityIsExhausted_ShouldReturnFalse() {
        testShip.putOrk(new Ork());
        assertFalse(testShip.putOrk(new Ork()));
    }
}
