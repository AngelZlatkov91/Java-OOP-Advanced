package football.core;

import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;

import static football.common.ConstantMessages.*;

import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepository();
        this.fields = new ArrayList<>();
    }


    @Override
    public String addField(String fieldType, String fieldName) {
        if (!(fieldType.equals("ArtificialTurf") || fieldType.equals("NaturalGrass"))) {
            throw new NullPointerException(INVALID_FIELD_TYPE);
        }

        Field field;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                fields.add(field);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                fields.add(field);
                break;

        }
        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);

    }

    @Override
    public String deliverySupplement(String type) {
        if (!(type.equals("Powdered") || type.equals("Liquid"))) {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        Supplement supplement;
        switch (type) {
            case "Powdered":
                supplement = new Powdered();
                supplementRepository.add(supplement);
                break;
            case "Liquid":
                supplement = new Liquid();
                supplementRepository.add(supplement);
        }
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);

    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Field field = getFieldByName(fieldName);
        Supplement supplement = supplementRepository.findByType(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND,supplementType));
        }
        field.addSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD,supplementType , fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerGender, String playerName, String nationality, int strength) {
        Field field = getFieldByName(fieldName);
        Player player;
        switch (playerGender) {
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }
        if (field.getClass().getSimpleName().equals("ArtificialTurf") && playerGender.equals("Women")) {
            field.addPlayer(player);
        } else if (field.getClass().getSimpleName().equals("NaturalGrass") && playerGender.equals("Men")) {
            field.addPlayer(player);;
        } else {
            return FIELD_NOT_SUITABLE;
        }
        return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerGender, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = getFieldByName(fieldName);
        for (Player p : field.getPlayer()) {
            p.stimulation();
        }
        return String.format(PLAYER_DRAG, field.getPlayer().size());
    }


    @Override
    public String getStatistics() {
        StringBuilder sb=new StringBuilder();
        for (Field field : this.fields) {
            sb.append(field.getInfo());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private Field getFieldByName(String fieldName) {
        return fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = getFieldByName(fieldName);
        int sumStrength = 0;
        for (Player p : field.getPlayer()) {
            sumStrength += p.getStrength();
        }

        return String.format(STRENGTH_FIELD, fieldName, sumStrength);
    }


}
