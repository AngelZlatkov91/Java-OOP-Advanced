package christmasRaces.entities.drivers;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;

public class DriverImpl implements Driver {
    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
        this.car = null;
        this.numberOfWins = 0;
        this.canParticipate = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        this.car = car;
        setCanParticipate(true);
    }

    @Override
    public void winRace() {
      setNumberOfWins(getNumberOfWins() + 1);
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME,name));
        }
        this.name = name;
    }


    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public void setCanParticipate(boolean canParticipate) {
        this.canParticipate = canParticipate;
    }
}
