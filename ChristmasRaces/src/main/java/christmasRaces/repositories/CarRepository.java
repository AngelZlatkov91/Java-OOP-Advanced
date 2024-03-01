package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;

public class CarRepository implements Repository<Car> {
    private Collection<Car> models;
    @Override
    public Car getByName(String name) {
        return this.models
                .stream()
                .filter(m->name.equals(m.getModel()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Car model) {
        this.models.add(model);
    }

    @Override
    public boolean remove(Car model) {
        return this.models.remove(model);
    }
}
