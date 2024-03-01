package handball.entities.gameplay;
import handball.entities.equipment.Equipment;
import handball.entities.team.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseGameplay implements Gameplay{
    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private Collection<Team> teams;

    public BaseGameplay(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.equipments = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Collection<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Collection<Team> getTeams() {
        return teams;
    }

    public void setTeams(Collection<Team> teams) {
        this.teams = teams;
    }

    @Override
    public int allProtection() {
      return this.equipments.stream().mapToInt(Equipment::getProtection).sum();
    }

    @Override
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        this.teams.remove(team);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        this.equipments.add(equipment);
    }

    @Override
    public void teamsInGameplay() {
        this.teams.forEach(e ->e.play());
    }

    @Override
    public String toString() {
        return  String.format("%s %s\nTeam: %s\nEquipment: %d, Protection: %d\n",
                this.name, this.getClass().getSimpleName(),
                this.teams.stream().map(Team::getName).collect(Collectors.joining(" ")),
                this.equipments.size(), allProtection());
    }
}
