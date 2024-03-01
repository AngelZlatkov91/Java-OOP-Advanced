package fairyShop.models;

public class Sleepy extends BaseHelper{
    private static final int INITIAL_ENERGY = 50;
    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work() {
        int currentEnergy = getEnergy();
        if ((currentEnergy - 5)< 0) {
            setEnergy(0);
        } else {
            setEnergy(currentEnergy - 5);
        }

    }

    @Override
    public void addInstrument(Instrument instrument) {
        getInstruments().add(instrument);

    }

    @Override
    public boolean canWork() {
        if (getEnergy() > 0) {
            return true;
        }
        return false;
    }

}
