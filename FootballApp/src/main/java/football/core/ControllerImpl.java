package football.core;
import football.common.ConstantMessages;
import football.common.ExceptionMessages;
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
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

import static football.common.ConstantMessages.FIELD_NOT_SUITABLE;

public class ControllerImpl implements Controller {
    private SupplementRepository supplement;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }
        this.fields.add(field);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE,fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplement1;
        switch (type) {
            case "Powdered":
                supplement1 = new Powdered();
                break;
            case "Liquid":
                supplement1 = new Liquid();
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);

        }
        this.supplement.add(supplement1);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement byType = this.supplement.findByType(supplementType);
        Field byField = findByField(fieldName);
        if (byType == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND,supplementType));
        }
        byField.addSupplement(byType);
        this.supplement.remove(byType);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD,supplementType,fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;
        Field byField = findByField(fieldName);
        switch (playerType) {
            case "Men":
                player = new Men(playerName,nationality,strength);
                break;
            case "Women":
                player = new Women(playerName,nationality,strength);
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        if (byField.getClass().getSimpleName().equals("ArtificialTurf") && playerType.equals("Women")) {
            byField.addPlayer(player);
        } else if (byField.getClass().getSimpleName().equals("NaturalGrass") && playerType.equals("Men")) {
            byField.addPlayer(player);;
        } else {
            return FIELD_NOT_SUITABLE;
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD,playerType,fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = findByField(fieldName);
        for (Player p : field.getPlayers()) {
            p.stimulation();
        }
        return String.format(ConstantMessages.PLAYER_DRAG,field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        int sumStrength = 0;
        Field byField = findByField(fieldName);
        for (Player p: byField.getPlayers()) {
            sumStrength += p.getStrength();
        }
        return String.format(ConstantMessages.STRENGTH_FIELD,fieldName,sumStrength);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.fields.forEach(f->sb.append(f.getInfo()).append(System.lineSeparator()));
        return sb.toString().trim();
    }


    private Field findByField(String filedName) {
        return this.fields
                .stream()
                .filter(f->filedName.equals(f.getName()))
                .findFirst()
                .orElse(null);
    }
}
