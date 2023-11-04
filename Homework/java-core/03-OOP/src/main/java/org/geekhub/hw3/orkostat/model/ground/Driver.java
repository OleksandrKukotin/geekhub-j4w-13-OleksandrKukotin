package org.geekhub.hw3.orkostat.model.ground;

import org.geekhub.hw3.orkostat.model.Collection;
import org.geekhub.hw3.orkostat.model.Ork;
import org.geekhub.hw3.orkostat.model.SimpleCollection;

public class Driver extends Ork {

    private final SimpleCollection driverLicenseCategories;

    public Driver() {
        this(new SimpleCollection(DriverLicenseCategory.B));
    }

    public Driver(SimpleCollection categories) {
        super(15_000);
        driverLicenseCategories = categories;
    }

    public Collection getLicenseCategories() {
        return driverLicenseCategories;
    }

    @Override
    public String scream() {
        return "Crap!";
    }
}
