package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double INITIAL_ENERGY = 60;
    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
       setEnergy(getEnergy() - 7);
       if (getEnergy() < 0) {
           setEnergy(0);
       }
    }
}
