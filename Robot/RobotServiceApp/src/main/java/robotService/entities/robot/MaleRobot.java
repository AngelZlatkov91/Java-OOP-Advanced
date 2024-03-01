package robotService.entities.robot;

public class MaleRobot extends BaseRobot{
    private static final int INITIAL_KILOGRAM = 9;
    public MaleRobot(String name, String kind,  double price) {
        super(name, kind, INITIAL_KILOGRAM, price);
    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + 3;
        super.setKilograms(newKilograms);
    }
}
