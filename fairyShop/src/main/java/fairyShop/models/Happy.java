package fairyShop.models;

public class Happy extends BaseHelper{
    private static final int INITIAL_ENERGY = 100;
    public Happy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work() {
        int currentEnergy = getEnergy();
        if ((currentEnergy - 10)< 0) {
            setEnergy(0);
        } else {
            setEnergy(currentEnergy - 10);
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
