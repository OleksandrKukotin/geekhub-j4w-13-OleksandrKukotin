package org.geekhub.hw3.orkostat;

import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.air.Aircraft;
import org.geekhub.hw3.orkostat.model.air.Pilot;
import org.geekhub.hw3.orkostat.model.ground.Driver;
import org.geekhub.hw3.orkostat.model.ground.Tank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrkoStatTest {

    private OrkoStat orkoStat;

    @BeforeEach
    void setUp() {
        orkoStat = new OrkoStat();
    }

    @Test
    void track_negatively_alive_orks() {
        orkoStat.smashOrk(new Ork());
        orkoStat.smashOrk(new Driver());
        orkoStat.smashOrk(new Pilot());

        int smashedOrks = orkoStat.getNegativelyAliveOrkCount();

        assertEquals(3, smashedOrks);
    }

    @Test
    void can_smash_empty_tank() {
        Tank tank = new Tank(3);
        orkoStat.smashTechnique(tank);

        assertEquals(0, orkoStat.getNegativelyAliveOrkCount());
    }

    @Test
    void can_smash_tank_with_equipage() {
        Tank tank = new Tank(3);
        tank.putOrk(new Driver());
        tank.putOrk(new Ork());

        orkoStat.smashTechnique(tank);

        assertEquals(2, orkoStat.getNegativelyAliveOrkCount());
    }

    @Test
    void combine_smashed_equipage_and_individual_orks() {
        Tank tank = new Tank(3);
        tank.putOrk(new Driver());
        tank.putOrk(new Ork());
        tank.putOrk(new Ork());

        orkoStat.smashOrk(new Ork());
        orkoStat.smashTechnique(tank);

        int orkCount = orkoStat.getNegativelyAliveOrkCount();

        assertEquals(4, orkCount);
    }

    @Test
    void zero_damage_in_the_beginning() {
        int damage = orkoStat.getLosesInDollars();

        assertEquals(0, damage);
    }

    @Test
    void individual_ork_increase_damage_in_dollars() {
        orkoStat.smashOrk(new Ork());

        int damage = orkoStat.getLosesInDollars();

        assertEquals(10_000, damage);
    }

    @Test
    void tank_without_equipage_and_ork_damage_cost_in_dollars() {
        orkoStat.smashTechnique(new Tank(6));

        int damageInDollars = orkoStat.getLosesInDollars();

        assertEquals(3_000_000, damageInDollars);
    }

    @Test
    void tank_with_equipage_and_ork_damage_cost_in_dollars() {
        Tank tank = new Tank(6);
        tank.putOrk(new Ork());
        tank.putOrk(new Driver());

        orkoStat.smashTechnique(tank);

        int damageInDollars = orkoStat.getLosesInDollars();
        assertEquals(3_025_000, damageInDollars);
    }

    @Test
    void multiple_techniques_with_equipage_and_ork_damage_cost_in_dollars() {
        Tank tank = new Tank(6);
        tank.putOrk(new Driver());
        Aircraft aircraft = new Aircraft(new Pilot(100));

        orkoStat.smashTechnique(aircraft);
        orkoStat.smashTechnique(tank);
        orkoStat.smashOrk(new Ork());

        int damageInDollars = orkoStat.getLosesInDollars();

        assertEquals(13_045_000, damageInDollars);
    }
}
