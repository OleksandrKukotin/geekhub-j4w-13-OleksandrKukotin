package org.geekhub.hw3.orkostat.model.ground;

import org.geekhub.hw3.orkostat.model.Collection;
import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.SimpleCollection;
import org.geekhub.hw3.orkostat.model.Technique;

public class Tank implements Technique {

    private final Collection equipage;
    private final int capacity;

    public Tank(int capacity) {
        equipage = new SimpleCollection(capacity);
        this.capacity = capacity;
    }

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

    @Override
    public Collection getEquipage() {
        return equipage;
    }

    @Override
    public int getPrice() {
        return 3_000_000;
    }

    @Override
    public String shoot() {
        return "Bam!";
    }
}
