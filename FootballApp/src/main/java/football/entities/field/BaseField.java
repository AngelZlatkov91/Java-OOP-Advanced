package football.entities.field;

import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseField implements Field{
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public int sumEnergy() {
        return this.supplements.stream().mapToInt(Supplement::getEnergy).sum();
    }

    @Override
    public void addPlayer(Player player) {
        if (getCapacity() == this.players.size()) {
            throw new IllegalStateException("Not enough capacity.");
        }
        this.players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        this.players.forEach(Player::stimulation);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" (").append(getClass().getSimpleName()).append("):").append(System.lineSeparator());
        sb.append("Player: ");
        if (this.players.isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            sb.append(this.players.stream().map(Player::getName).collect(Collectors.joining(" ")));
            sb.append(System.lineSeparator());
        }
        sb.append("Supplement: ").append(this.supplements.size()).append(System.lineSeparator());
        sb.append("Energy: ").append(sumEnergy());
        return sb.toString().trim();
    }
}
