package org.geekhub.hw3.orkostat.model.air;

import org.geekhub.hw3.orkostat.model.Ork;

public class Pilot extends Ork {

    private int flewHours;

    public Pilot() {
        super(20_000);
        flewHours = 0;
    }

    public Pilot(int flewHours) {
        super(20_000);
        this.flewHours = flewHours;
    }

    @Override
    public String scream() {
        return "Hell!";
    }

    public int getFlewHours() {
        return flewHours;
    }
}
