package robotService.entities.services;

import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;

public class MainService extends BaseService{
    public MainService(String name) {
        super(name, 30);
    }

    @Override
    public void addRobot(Robot robot) {
        if(robot instanceof MaleRobot) {
            super.addRobot(robot);
        }
        throw new IllegalArgumentException();
    }
}
