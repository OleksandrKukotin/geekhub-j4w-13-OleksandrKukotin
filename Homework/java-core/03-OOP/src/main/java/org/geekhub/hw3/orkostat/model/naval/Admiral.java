package org.geekhub.hw3.orkostat.model.naval;

import org.geekhub.hw3.orkostat.model.Ork;

public class Admiral extends Ork {

    private int navigationHours;

    public Admiral(int navigationHours) {
        super(30_000);
        this.navigationHours = navigationHours;
    }

    public Admiral(int price, int navigationHours) {
        super(price);
        this.navigationHours = navigationHours;
    }

    @Override
    public String scream() {
        return "We surfaced negatively!";
    }

    public int getNavigationHours() {
        return navigationHours;
    }

    public void setNavigationHours(int navigationHours) {
        this.navigationHours = navigationHours;
    }
}
