package handball.entities.equipment;

public class Kneepad extends BaseEquipment {
    private static final int INITIALIZATION_PROTECTION = 120;
    private static final double INITIALIZATION_PRICE = 15;
    public Kneepad() {
        super(INITIALIZATION_PROTECTION, INITIALIZATION_PRICE);
    }
}
