package org.geekhub.hw3.orkostat.model.ground;

import org.geekhub.hw3.orkostat.model.Collection;
import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.SimpleCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TankTest {

    @Test
    void tank_price_is_3_000_000() {
        Tank tank = new Tank(3);

        assertEquals(3_000_000, tank.getPrice());
    }

    @Test
    void empty_tank_has_zero_equipage() {
        Tank tank = new Tank(3);

        int size = tank.getEquipage().size();

        assertEquals(0, size);
    }

    @Test
    void tank_can_have_some_equipage() {
        Tank tank = new Tank(3);
        tank.putOrk(new Ork());

        int size = tank.getEquipage().size();

        assertEquals(1, size);
    }

    @Test
    void should_not_put_ork_when_same_ork_put_again() {
        Tank tank = new Tank(3);
        Ork ork = new Ork();
        tank.putOrk(ork);
        tank.putOrk(ork);

        int size = tank.getEquipage().size();

        assertEquals(1, size);
    }

    @Test
    void tank_equipage_cannot_be_more_than_technical_max() {
        Tank tank = new Tank(3);
        tank.putOrk(new Ork());
        tank.putOrk(new Ork());
        tank.putOrk(new Ork());

        boolean sit = tank.putOrk(new Ork());

        assertFalse(sit);
        assertEquals(3, tank.getEquipage().size());
    }

    @Test
    void tank_driver_can_have_driver_license() {
        Tank tank = new Tank(3);
        Driver driver = new Driver(new SimpleCollection(DriverLicenseCategory.B, DriverLicenseCategory.BE));

        tank.putOrk(driver);

        assertEquals(1, tank.getEquipage().size());
        Driver actualDriver = (Driver) tank.getEquipage().getElements()[0];
        assertEquals(15_000, actualDriver.getPrice());

        Collection actualDriverLicenseCategories = actualDriver.getLicenseCategories();
        assertEquals(2, actualDriverLicenseCategories.size());
        assertEquals(DriverLicenseCategory.B, actualDriverLicenseCategories.getElements()[0]);
        assertEquals(DriverLicenseCategory.BE, actualDriverLicenseCategories.getElements()[1]);
    }

    @Test
    void shoot() {
        Tank tank = new Tank(3);
        tank.putOrk(new Driver());
        tank.putOrk(new Ork());

        String shoot = tank.shoot();

        assertEquals("Bam!", shoot);
    }

    @Test
    void destroy_empty() {
        Tank tank = new Tank(3);

        String result = tank.destroy();

        assertEquals("Destroyed!", result);
    }

    @Test
    void destroy_non_empty() {
        Tank tank = new Tank(3);
        tank.putOrk(new Driver());
        tank.putOrk(new Ork());
        tank.putOrk(new Ork());

        String result = tank.destroy();

        assertEquals("Destroyed!Crap!Aaaaaa!Aaaaaa!", result);
    }
}
