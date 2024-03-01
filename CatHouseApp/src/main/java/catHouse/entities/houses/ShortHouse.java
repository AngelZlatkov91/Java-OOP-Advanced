package catHouse.entities.houses;

import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

public class ShortHouse extends BaseHouse {
    private static final int INITIAL_CAPACITY = 15;

    public ShortHouse(String name) {
        super(name, INITIAL_CAPACITY);
    }

    @Override
    public int sumSoftness() {
        int sum = 0;
        for (Toy toy : super.getToys()) {
            sum +=toy.getSoftness();
        }
        return sum;
    }

    @Override
    public void addCat(Cat cat) {
        if (INITIAL_CAPACITY == super.getCats().size()) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        super.getCats().add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        super.getCats().remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        super.getToys().add(toy);
    }

    @Override
    public void feeding() {
        super.getCats().forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" ").append(getClass().getSimpleName()).append(":").append(System.lineSeparator());
        sb.append("Cats: ");
        if (super.getCats().isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            super.getCats().forEach(c->{
                sb.append(c.getName()).append(" ");
            });
            sb.append(System.lineSeparator());
        }
        sb.append("Toys: ").append(super.getToys().size()).append(" Softness: ").append(sumSoftness()).append(System.lineSeparator());
        return sb.toString().trim();
    }

}
