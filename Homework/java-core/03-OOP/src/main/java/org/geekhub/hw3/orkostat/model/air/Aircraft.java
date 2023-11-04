package org.geekhub.hw3.orkostat.model.air;

import org.geekhub.hw3.orkostat.model.Collection;
import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.SimpleCollection;
import org.geekhub.hw3.orkostat.model.Technique;

public class Aircraft implements Technique {
    private final Collection equipage;

    public Aircraft(Pilot pilot) {
        this.equipage = new SimpleCollection(pilot);
    }

    @Override
    public String destroy() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Destroyed!");
        Pilot pilot = (Pilot) equipage.getElements()[0];
        stringBuilder.append(pilot.scream());
        return stringBuilder.toString();
    }

    @Override
    public Collection getEquipage() {
        return equipage;
    }

    @Override
    public int getPrice() {
        return 10_000_000;
    }

    @Override
    public String shoot() {
        return "Pew-pew-pew!";
    }

    @Override
    public boolean putOrk(Ork ork) {
        return false;
    }
}
