package harpoonDiver.models.diver;

import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.seaCatch.BaseSeaCatch;
import harpoonDiver.models.seaCatch.SeaCatch;

public abstract class BaseDiver implements Diver{
    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    public BaseDiver(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.seaCatch = new BaseSeaCatch();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.DIVER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ExceptionMessages.DIVER_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public SeaCatch getSeaCatch() {
        return seaCatch;
    }

    public void setSeaCatch(SeaCatch seaCatch) {
        this.seaCatch = seaCatch;
    }

    @Override
    public void shoot() {
        if (getOxygen() - 30  > 0) {
            setOxygen(getOxygen() - 30);
        } else {
            setOxygen(0);
        }
    }

    @Override
    public boolean canDive() {
        if(getOxygen() > 0) {
            return true;
        }
        return false;
    }
}
