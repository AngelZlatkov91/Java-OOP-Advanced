package catHouse.entities.toys;

public class Mouse extends BaseToy{
    private static final int INITIAL_SOFTNESS = 5;
    private static final double INITIAL_PRICE = 15;

    public Mouse() {
        super(INITIAL_SOFTNESS, INITIAL_PRICE);
    }
}
