package handball.core;

import handball.common.ConstantMessages;
import handball.common.ExceptionMessages;
import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;
import handball.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private Repository equipment;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipment = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    public Repository getEquipment() {
        return equipment;
    }

    public Collection<Gameplay> getGameplays() {
        return gameplays;
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        Gameplay gameplay = null;
        switch (gameplayType) {
            case "Outdoor":
                gameplay = new Outdoor(gameplayName);
                break;
            case "Indoor":
                gameplay = new Indoor(gameplayName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_GAMEPLAY_TYPE);
        }
        this.gameplays.add(gameplay);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_GAMEPLAY_TYPE,gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        Equipment equipment1 = null;
        switch (equipmentType) {
            case "Kneepad":
                equipment1 = new Kneepad();
                break;
            case "ElbowPad":
                equipment1 = new ElbowPad();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_EQUIPMENT_TYPE);
        }
        this.equipment.add(equipment1);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_TYPE,equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment byType = this.equipment.findByType(equipmentType);
        if (byType == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_EQUIPMENT_FOUND,equipmentType));
        }
        this.equipment.remove(byType);
        Gameplay gameplay = this.gameplays.stream().filter(g -> gameplayName.equals(g.getName())).findFirst().orElse(null);
             gameplay.addEquipment(byType);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY,equipmentType,gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team team = null;
        if (teamType.equals("Bulgaria")) {
            if (gameplayName.equals("Outdoor")) {
                team =  new Bulgaria(teamName,country,advantage);
            }
        } else if (teamType.equals("Germany")) {
            if (gameplayName.equals("Indoor")) {
                team =  new Germany(teamName,country,advantage);
            }
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TEAM_TYPE);
        }
        if (team== null) {
            return ConstantMessages.GAMEPLAY_NOT_SUITABLE;
        }
        Gameplay gameplay = this.gameplays.stream().filter(g -> gameplayName.equals(g.getName())).findFirst().orElse(null);
        gameplay.addTeam(team);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY,teamType,gameplayName);
    }

    @Override
    public String playInGameplay(String gameplayName) {
        Gameplay gameplay = this.gameplays.stream().filter(g -> gameplayName.equals(g.getName())).findFirst().orElse(null);
        gameplay.teamsInGameplay();
        return String.format(ConstantMessages.TEAMS_PLAYED,gameplay.getTeams().size());
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        Gameplay gameplay = this.gameplays.stream().filter(g -> gameplayName.equals(g.getName())).findFirst().orElse(null);
          int sum = gameplay.getTeams().stream().mapToInt(Team::getAdvantage).sum();
        return String.format(ConstantMessages.ADVANTAGE_GAMEPLAY,gameplayName,sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.gameplays.forEach(e->sb.append(e.toString()));
        return sb.toString().trim();
    }
}
