package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }


    @Override
    public String addHouse(String type, String name) {
        House house = null;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case"LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        this.houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy = null;
        switch (type){
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy first = this.toys.findFirst(toyType);
        if (first == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND,toyType));
        }
        this.toys.removeToy(first);
        House house = this.houses.stream().filter(h -> houseName.equals(h.getName())).findFirst().orElse(null);
        house.buyToy(first);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat = null;
        switch (catType){
            case "ShorthairCat":
                cat = new ShorthairCat(catName,catBreed,price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName,catBreed,price);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        if (catType.equals("ShorthairCat") && houseName.equals("LongHouse") || catType.equals("LonghairCat") && houseName.equals("ShortHouse")) {
             return ConstantMessages.UNSUITABLE_HOUSE;
        } else {
            House house = this.houses.stream().filter(h -> houseName.equals(h.getName())).findFirst().orElse(null);
            house.addCat(cat);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);
        }

    }

    @Override
    public String feedingCat(String houseName) {
        int count = 0;
        House house = this.houses.stream().filter(h -> houseName.equals(h.getName())).findFirst().orElse(null);
        for (Cat cat : house.getCats()) {
            cat.eating();
            count++;
        }
        return String.format(ConstantMessages.FEEDING_CAT,count);
    }

    @Override
    public String sumOfAll(String houseName) {
        double allSum = 0;
        House house = this.houses.stream().filter(h -> houseName.equals(h.getName())).findFirst().orElse(null);
        for (Toy toy : house.getToys()) {
            allSum += toy.getPrice();
        }
        for (Cat cat : house.getCats()) {
            allSum+= cat.getPrice();
        }
        return String.format(ConstantMessages.VALUE_HOUSE,houseName,allSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.houses.forEach(h-> {
            sb.append(h.getStatistics());
        });
        return sb.toString().trim();
    }
}
