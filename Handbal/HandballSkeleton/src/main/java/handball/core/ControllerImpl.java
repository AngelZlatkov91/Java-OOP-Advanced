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

import java.util.HashMap;
import java.util.Map;

public class ControllerImpl implements Controller{
    private EquipmentRepository equipment;
    private Map<String,Gameplay> gameplays;

    public ControllerImpl() {
        this.equipment = new EquipmentRepository();
        this.gameplays = new HashMap<>();
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
        this.gameplays.put(gameplayName,gameplay);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_GAMEPLAY_TYPE,gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        Equipment equipment1 = null;
        switch (equipmentType){
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
            throw new IllegalArgumentException(ExceptionMessages.NO_EQUIPMENT_FOUND);
        }
        this.equipment.remove(byType);
        this.gameplays.get(gameplayName).addEquipment(byType);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY,equipmentType,gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team team = null;
        switch (teamType) {
            case "Bulgaria":
                team = new Bulgaria(teamName,country,advantage);
                break;
            case "Germany":
                team = new Germany(teamName,country,advantage);
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.INVALID_TEAM_TYPE);
        }
        Gameplay gameplay = gameplays.get(gameplayName);
        boolean isSuitable = gameplay.getClass().getSimpleName().equals("Outdoor") &&
                teamType.equals("Bulgaria") || gameplay.getClass().getSimpleName().equals("Indoor") &&
                teamType.equals("Germany");
        if (!isSuitable) {
            return String.format(ConstantMessages.GAMEPLAY_NOT_SUITABLE);
        }
        this.gameplays.get(gameplayName).addTeam(team);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY,teamType,gameplayName);
    }

    @Override
    public String playInGameplay(String gameplayName) {
        Gameplay gameplay = this.gameplays.get(gameplayName);
        gameplay.teamsInGameplay();
        return String.format(ConstantMessages.TEAMS_PLAYED,gameplay.getTeams().size());
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        Gameplay gameplay = this.gameplays.get(gameplayName);
        int sum = gameplay.getTeams().stream().mapToInt(Team::getAdvantage).sum();
        return String.format(ConstantMessages.ADVANTAGE_GAMEPLAY,gameplay,sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.gameplays.values().forEach(sb::append);
        return sb.toString().trim();
    }
}
