package org.geekhub.hw3.orkostat.model;

public interface Technique {

    default String destroy() {
        StringBuilder sounds = new StringBuilder();
        sounds.append("Destroyed!");
        for (Object object : this.getEquipage().getElements()) {
            if (object instanceof Ork ork) {
                sounds.append(ork.scream());
            }
        }
        return sounds.toString();
    }

    Collection getEquipage();

    int getPrice();

    String shoot();
}
