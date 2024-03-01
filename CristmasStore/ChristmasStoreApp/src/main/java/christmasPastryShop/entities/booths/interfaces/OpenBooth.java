package christmasPastryShop.entities.booths.interfaces;

public class OpenBooth extends BaseBooth {
    public static final double PRICE_PER_PERSON = 2.50;
    public OpenBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, PRICE_PER_PERSON);
    }
}
