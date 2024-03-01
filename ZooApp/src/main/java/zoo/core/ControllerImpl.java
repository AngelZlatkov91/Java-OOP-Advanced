package zoo.core;
import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area = null;
        switch (areaType) {
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
            default:throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }
        this.areas.add(area);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE,areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food = null;
        switch (foodType) {
            case "Vegetable":
                food = new Vegetable();
                break;
            case "Meat":
                food = new Meat();
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }
        this.foodRepository.add(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE,foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food byType = this.foodRepository.findByType(foodType);
        Area area = this.areas.stream().filter(a -> areaName.equals(a.getName())).findFirst().orElse(null);
        if (byType == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND,foodType));
        }
        area.addFood(byType);
      // this.foodRepository.remove(byType);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA,foodType,areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal = null;
        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName,kind,price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName,kind,price);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }
       Area area = this.areas.stream().filter(e -> areaName.equals(e.getName())).findFirst().orElse(null);
        if (area.getClass().getSimpleName().equals("WaterArea") && animalType.equals("AquaticAnimal")) {
            area.addAnimal(animal);
        } else if (area.getClass().getSimpleName().equals("LandArea") && animalType.equals("TerrestrialAnimal")) {
            area.addAnimal(animal);
        } else {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }
        area.addAnimal(animal);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA,animalType,areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = this.areas.stream().filter(a -> areaName.equals(a.getName())).findFirst().orElse(null);
          area.getAnimals().forEach(Animal::eat);
        return String.format(ConstantMessages.ANIMALS_FED,area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = this.areas.stream().filter(a -> areaName.equals(a.getName())).findFirst().orElse(null);
        double allKg =0;
               for(Animal a : area.getAnimals()) {
                   allKg += a.getKg();
               }
        return String.format(ConstantMessages.KILOGRAMS_AREA,areaName,allKg);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.areas.forEach(e->sb.append(e.getInfo()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
