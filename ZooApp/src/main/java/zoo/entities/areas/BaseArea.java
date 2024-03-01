package zoo.entities.areas;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;
import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseArea implements Area{
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
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
    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return animals;
    }

    @Override
    public int sumCalories() {
        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.capacity == this.animals.size()) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        this.animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" (").append(getClass().getSimpleName()).append("):").append(System.lineSeparator());
        sb.append("Animals: ");
        if (this.animals.isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            this.animals.forEach(a-> sb.append(a.getName()).append(" "));
            sb.append(System.lineSeparator());
        }
        sb.append("Calories: ").append(this.foods.stream().mapToInt(Food::getCalories).sum()).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
