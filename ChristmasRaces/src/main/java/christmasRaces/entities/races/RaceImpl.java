package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
       if (driver == null) {
           throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
       }
       if (!driver.getCanParticipate()) {
           throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,driver.getName()));
       }
       if (this.drivers.contains(driver)) {
           throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,driver.getName(),getName()));
       }
       this.drivers.add(driver);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5 ){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME,name));

        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_LAPS);
        }
        this.laps = laps;
    }
}
