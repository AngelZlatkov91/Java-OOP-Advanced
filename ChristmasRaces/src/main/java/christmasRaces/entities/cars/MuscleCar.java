package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar {
    private static final double INITIAL_CUBICMETERS = 5000;
    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, INITIAL_CUBICMETERS);
    }

    @Override
    public double calculateRacePoints(int laps) {
        return INITIAL_CUBICMETERS / (getHorsePower() * laps);
    }

    @Override
    public void setHorsePower(int horsePower) {
        if (horsePower < 400 || horsePower > 600) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
