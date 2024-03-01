package robotService.entities.services;

import robotService.common.ExceptionMessages;
import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public  abstract class BaseService implements Service{
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public Collection<Robot> getRobots() {
        return robots;
    }

    @Override
    public int sumHardness() {

        return this.getSupplements()
                .stream()
                .mapToInt(Supplement::getHardness)
                .sum();
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.capacity == this.robots.size()) {
            throw new IllegalArgumentException("Not enough capacity for this robot.");
        }
        this.robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void feeding() {
        this.robots.forEach(Robot::eating);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" ").append(getClass().getSimpleName()).append(":").append(System.lineSeparator());
        sb.append("Robots: ");
        if (this.robots.isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            sb.append(this.robots.stream().map(Robot::getName).collect(Collectors.joining(" "))).append(System.lineSeparator());
        }
        sb.append("Supplements: ").append(this.supplements.size()).append(" Hardness: ").append(this.sumHardness()).append(System.lineSeparator());
        return sb.toString();
    }
}
