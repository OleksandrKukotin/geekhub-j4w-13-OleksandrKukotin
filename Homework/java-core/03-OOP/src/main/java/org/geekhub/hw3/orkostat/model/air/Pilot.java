package org.geekhub.hw3.orkostat.model.air;

import org.geekhub.hw3.orkostat.model.Ork;

public class Pilot extends Ork {

    private int flewHours;

    public Pilot() {
        flewHours = 0;
        setPrice(20_000);
    }

    public Pilot(int flewHours) {
        this.flewHours = flewHours;
        setPrice(20_000);
    }

    @Override
    public String scream() {
        return "Hell!";
    }

    public int getFlewHours() {
        return flewHours;
    }
}
