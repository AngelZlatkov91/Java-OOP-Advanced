package viceCity.models.neighbourhood;
import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        List<Gun> models = (List<Gun>) mainPlayer.getGunRepository().getModels();
        List<Player> players = new ArrayList<>();
        for (Player pl : civilPlayers) {
            players.add(pl);
        }
        if (!models.isEmpty() && mainPlayer.isAlive()) {
            while (!players.isEmpty())  {
                if (models.isEmpty()) {
                    break;
                }
                Gun gun = models.get(0);
                int fire = gun.fire();
                Player player = players.get(0);
                player.takeLifePoints(fire);
                if (!player.isAlive()) {
                    players.remove(0);
                }
                if (!gun.canFire()) {
                    models.remove(0);
                }
            }
        }
        if (!players.isEmpty()) {
            while (mainPlayer.isAlive() || !players.isEmpty()) {
                Player player = players.get(0);
                List<Gun> guns = (List<Gun>) player.getGunRepository().getModels();
                if (!guns.isEmpty()) {
                    while (!guns.isEmpty() || mainPlayer.isAlive()) {
                        Gun gun = guns.get(0);
                        int fire = gun.fire();
                        mainPlayer.takeLifePoints(fire);
                        if (!gun.canFire()) {
                            guns.remove(0);
                        }
                    }
                    if (mainPlayer.isAlive()) {
                        players.remove(0);
                    }
                } else {
                    break;
                }
            }
        }


    }


}
