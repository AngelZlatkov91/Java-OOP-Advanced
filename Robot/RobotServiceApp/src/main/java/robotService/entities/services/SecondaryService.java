package robotService.entities.services;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.Robot;

public class SecondaryService extends BaseService{
    public SecondaryService(String name) {
        super(name, 15);

    }
    @Override
    public void addRobot(Robot robot) {
        if(robot instanceof FemaleRobot) {
            super.addRobot(robot);
        }
        throw new IllegalArgumentException();
    }
}
