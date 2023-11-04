package org.geekhub.hw3.orkostat.model;

public interface Technique {

    String destroy();

    Collection getEquipage();

    int getPrice();

    String shoot();

    boolean putOrk(Ork ork);
}
