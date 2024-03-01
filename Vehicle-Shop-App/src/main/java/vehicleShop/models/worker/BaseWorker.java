package vehicleShop.models.worker;

import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.tool.Tool;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseWorker implements Worker{
    private String name; // име на работника
     private int strength;   // сила на работника
    private Collection<Tool> tools; // списък с инструменти

    // конструктор


    public BaseWorker(String name, int strength) {
        this.setName(name);
        this.setStrength(strength);
        this.tools = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals(" ")) {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength < 0) {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public Collection<Tool> getTools() {
        return tools;
    }

    public void setTools(Collection<Tool> tools) {
        this.tools = tools;
    }


    @Override
    public void working() {
        int currentStrength = getStrength();// текуща сила на работника
        currentStrength -= 10;
        if (currentStrength < 0) {
            currentStrength = 0;
        }
        setStrength(currentStrength);
    }

    @Override
    public void addTool(Tool tool) {
       this.getTools().add(tool);
    }

    @Override
    public boolean canWork() {

        return this.getStrength() > 0;
    }

    @Override
    public String toString() {
        long leftTools = this.tools.stream().filter(tool -> tool.getPower() > 0).count();
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name + ", Strength: " + strength).append(System.lineSeparator());
        sb.append("Tools: " + leftTools + " fit left");
        return sb.toString().trim();

    }
}
