package christmasPastryShop.core;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.common.OutputMessages;
import christmasPastryShop.common.enums.BoothType;
import christmasPastryShop.common.enums.CocktailType;
import christmasPastryShop.common.enums.DelicacyType;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

public class ControllerImpl implements Controller {
    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalIncome;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository,
                          CocktailRepository<Cocktail> cocktailRepository,
                          BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
    }

    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = delicacyRepository.getByName(name);
        if (delicacy != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,delicacy.getClass().getSimpleName(),name));
        }
        DelicacyType delicacyType = DelicacyType.valueOf(type);
            switch (delicacyType) {
                case Stolen:
                    delicacy = new Stolen(name,price);
                    break;
                case Gingerbread:
                    delicacy = new Gingerbread(name,price);
                    break;

        }

        delicacyRepository.add(delicacy);
        return String.format(OutputMessages.DELICACY_ADDED,name,type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = cocktailRepository.getByName(name);
        if (cocktail != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,cocktail.getClass().getSimpleName(),name));
         }
        CocktailType cocktailType = CocktailType.valueOf(type);
            switch (cocktailType) {
                case Hibernation:
                    cocktail = new Hibernation(name,size,brand);
                    break;
                case MulledWine:
                    cocktail = new MulledWine(name,size,brand);
                    break;
            }

        cocktailRepository.add(cocktail);

        return String.format(OutputMessages.COCKTAIL_ADDED,name,brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        if (booth != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BOOTH_EXIST,boothNumber));
        }
        BoothType boothType = BoothType.valueOf(type);

        switch (boothType) {
            case OpenBooth:
                booth = new OpenBooth(boothNumber,capacity);
                break;
            case PrivateBooth:
                booth = new PrivateBooth(boothNumber,capacity);
                break;
        }
        boothRepository.add(booth);
        return String.format(OutputMessages.BOOTH_ADDED,boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth booth1 = boothRepository.getAll().stream()
                .filter(booth -> !booth.isReserved())
                .filter(booth -> booth.getCapacity() >= numberOfPeople)
                .findFirst()
                .orElse(null);
        String resultMsg = String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,numberOfPeople);

        if (booth1 != null) {
            booth1.reserve(numberOfPeople);
            resultMsg = String.format(OutputMessages.BOOTH_RESERVED,booth1.getBoothNumber(),numberOfPeople);
        }
        return resultMsg;
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        this.totalIncome += bill;
        booth.clear();
        return String.format(OutputMessages.BILL,boothNumber,bill);
    }

    @Override
    public String getIncome() {

        return String.format(OutputMessages.TOTAL_INCOME,this.totalIncome);
    }
}
