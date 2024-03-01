package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.Collection;

public interface Field {
    int sumEnergy();

    void addPlayer(Player player);

    void removePlayer(Player player);

    void addSupplement(Supplement supplement);

    void drag();

    String getInfo();

    Collection<Player> getPlayer();

    Collection<Supplement> getSupplement();

    String getName();


}
