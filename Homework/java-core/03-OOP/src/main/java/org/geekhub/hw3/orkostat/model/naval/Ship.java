package org.geekhub.hw3.orkostat.model.naval;

import org.geekhub.hw3.orkostat.model.Collection;
import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.SimpleCollection;
import org.geekhub.hw3.orkostat.model.Technique;

public class Ship implements Technique {

    private SimpleCollection equipage;
    private final int capacity;

    public Ship(Admiral admiral, int capacity) {
        this.capacity = capacity;
        equipage = new SimpleCollection(capacity);
        equipage.add(admiral);
    }

    public Ship(Admiral admiral, int capacity, Ork... orks) {
        this.capacity = capacity;
        equipage = new SimpleCollection(capacity);
        equipage.add(admiral);
        for (Ork ork : orks) {
            this.putOrk(ork);
        }
    }

    @Override
    public String destroy() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Destroyed!");
        for (Object seaman : equipage.getElements()) {
            if (seaman instanceof Ork ork) {
                stringBuilder.append(ork.scream());
            }
        }
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
        return "BOOM!";
    }

    @Override
    public boolean putOrk(Ork ork) {
        if (equipage.size() + 1 > capacity) {
            return false;
        }
        for (Object member : equipage.getElements()) {
            if (member.equals(ork)) {
                return false;
            }
        }
        equipage.add(ork);
        return true;
    }
}
