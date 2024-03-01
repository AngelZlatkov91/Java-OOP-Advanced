package magicGame.models.magicians;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;

public abstract class MagicianImpl implements Magician{
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    public MagicianImpl(String username, int health, int protection, Magic magic) {
        this.setUsername(username);
        this.setHealth(health);
        this.setProtection(protection);
        this.setMagic(magic);
        this.isAlive = true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public Magic getMagic() {
        return magic;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    public void setProtection(int protection) {
        if (protection < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    public void setMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC);
        }
        this.magic = magic;
    }

    public void setAlive(boolean alive) {

        isAlive = alive;
    }

    @Override
    public void takeDamage(int points) {
        if (getProtection() > 0) {
            setProtection(getProtection() - points);
        } else {
            if (getHealth() > 0) {
                setHealth(getHealth() - points);
            } else {
                setAlive(false);
            }
        }
    }
}
