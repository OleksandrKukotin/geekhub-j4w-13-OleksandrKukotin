package org.geekhub.hw3.orkostat.model.ground;

import org.geekhub.hw3.orkostat.model.Collection;
import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.SimpleCollection;

public class Driver extends Ork {

    private final Collection licenseCategories;

    public Driver() {
        this(new SimpleCollection());
    }

    public Driver(Collection licenseCategories) {
        super(15_000);
        this.licenseCategories = licenseCategories;
    }

    public Collection getLicenseCategories() {
        return licenseCategories;
    }

    @Override
    public String scream() {
        return "Crap!";
    }
}
