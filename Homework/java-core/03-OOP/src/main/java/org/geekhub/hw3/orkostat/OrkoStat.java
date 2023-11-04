package org.geekhub.hw3.orkostat;

import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.Technique;

public class OrkoStat {

    private int negativelyAliveOrkCount;

    private int losesInDollars;

    public OrkoStat() {
        negativelyAliveOrkCount = 0;
        losesInDollars = 0;
    }

    public void smashOrk(Ork ork) {
        negativelyAliveOrkCount++;
        losesInDollars += ork.getPrice();
    }

    public void smashTechnique(Technique techUnit) {
        losesInDollars += techUnit.getPrice();
        for (Object object : techUnit.getEquipage().getElements()) {
            if (object instanceof Ork eliminated) {
                losesInDollars += eliminated.getPrice();
                negativelyAliveOrkCount++;
            }
        }
    }

    public int getLosesInDollars() {
        return losesInDollars;
    }

    public int getNegativelyAliveOrkCount() {
        return negativelyAliveOrkCount;
    }
}
