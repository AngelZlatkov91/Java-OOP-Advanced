package robotService.entities.robot;

public class FemaleRobot extends BaseRobot {
    private static final int INITIAL_KILOGRAM = 7;

    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, INITIAL_KILOGRAM, price);
    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + 1;
         super.setKilograms(newKilograms);
    }
}
