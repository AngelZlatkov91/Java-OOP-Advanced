package football.entities.player;

import static football.common.ExceptionMessages.*;


public abstract class BasePlayer implements Player {
    private String name;
    private String nationality;
    private double kg;
    private int strength;

    public BasePlayer(String name, String nationality, int strength) {
        this.setName(name);
        this.setNationality(nationality);
        this.setStrength(strength);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (ifNullOrEmptyString(name)) {
            throw new NullPointerException(PLAYER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public void stimulation() {
        strength += 45;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        if (ifNullOrEmptyString(name)) {
            throw new NullPointerException(PLAYER_NATIONALITY_NULL_OR_EMPTY);
        }
        this.nationality = nationality;
    }

    public double getKg() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength <= 0) {
            throw new IllegalArgumentException(PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO);
        }
        this.strength = strength;
    }

    private boolean ifNullOrEmptyString(String name) {
        return name == null || name.trim().isEmpty();
    }

}
