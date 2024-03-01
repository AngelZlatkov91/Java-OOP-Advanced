package aquarium.entities.aquariums;

import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseAquarium implements Aquarium{
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }
    @Override
    public Collection<Decoration> getDecorations() {
        return decorations;
    }

    @Override
    public Collection<Fish> getFish() {
        return fish;
    }

    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public void addFish(Fish fish) {
        if (getCapacity() == this.fish.size()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):",getName(),getClass().getSimpleName())).append(System.lineSeparator());
        sb.append("Fish: ");
        if (this.fish.isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            this.fish.forEach(f->sb.append(f.getName()).append(" "));
            sb.append(System.lineSeparator());
        }
        sb.append("Decorations: ").append(this.decorations.size()).append(System.lineSeparator());
        sb.append("Comfort: ").append(calculateComfort()).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
