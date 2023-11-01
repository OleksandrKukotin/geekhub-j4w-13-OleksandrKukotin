package org.geekhub.hw3.orkostat.model.air;

import org.geekhub.hw3.orkostat.model.Ork;

public class Pilot extends Ork {

    private int flewHours;

    public int getFlewHours() {
        return flewHours;
    }

    public void setFlewHours(int flewHours) {
        this.flewHours = flewHours;
    }
}
