package org.geekhub.hw3.orkostat.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrkTest {
    @Test
    void default_ork_price_is_lada_vesta_sport() {
        final Ork ork = new Ork();

        assertEquals(10_000, ork.getPrice());
    }

    @Test
    void major_ork_price_can_differ_to_lada_vesta_sport() {
        final Ork ork = new Ork(30_000);

        assertEquals(30_000, ork.getPrice());
    }

    @Test
    void ork_price_can_be_increased_on_demand() {
        final Ork ork = new Ork();
        ork.setPrice(50_000);

        assertEquals(50_000, ork.getPrice());
    }
}
