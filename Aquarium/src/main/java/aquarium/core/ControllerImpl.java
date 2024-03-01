package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private DecorationRepository decorationRepository;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorationRepository = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium = null;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        this.aquariums.add(aquarium);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE,aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration = null;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }
        this.decorationRepository.add(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE,type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = this.decorationRepository.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND,decorationType));
        }
        Aquarium aquarium = findAquarium(aquariumName);
        aquarium.addDecoration(decoration);
        this.decorationRepository.remove(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM,decorationType,aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish = null;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName,fishSpecies,price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName,fishSpecies,price);
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        Aquarium aquarium = findAquarium(aquariumName);
        if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium") && fishType.equals("SaltwaterFish")) {
            aquarium.addFish(fish);
        } else if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium") && fishType.equals("FreshwaterFish")) {
            aquarium.addFish(fish);
        } else {
            return ConstantMessages.WATER_NOT_SUITABLE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM,fishType,aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = findAquarium(aquariumName);
        aquarium.feed();
        return String.format(ConstantMessages.FISH_FED,aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        double sum = 0;
        Aquarium aquarium = findAquarium(aquariumName);
        for (Fish fish: aquarium.getFish()) {
            sum += fish.getPrice();
        }
        for (Decoration decoration : aquarium.getDecorations()) {
            sum += decoration.getPrice();
        }
        return String.format(ConstantMessages.VALUE_AQUARIUM,aquariumName,sum);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        this.aquariums.forEach(a-> sb.append(a.getInfo()));
        return sb.toString().trim();
    }
    private Aquarium findAquarium (String aquariumName) {
       return this.aquariums
                .stream()
                .filter(a->aquariumName.equals(a.getName()))
                .findFirst()
                .orElse(null);
    }
}
