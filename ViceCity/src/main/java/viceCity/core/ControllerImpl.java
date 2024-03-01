package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.*;


public class ControllerImpl implements Controller {
    private GunRepository gunRepository;
    private Map<String,Player> players;
    private Neighbourhood neighbourhood;
    private Player mainPlayer;

    public ControllerImpl() {
        this.gunRepository = new GunRepository();
        this.players = new LinkedHashMap<>();
        this.neighbourhood = new GangNeighbourhood();
        this.mainPlayer = new MainPlayer();
    }

    public GunRepository getGunRepository() {
        return gunRepository;
    }

    public Collection<Player> getPlayers() {

        return this.players.values();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.players.put(name,player);
        return String.format(ConstantMessages.PLAYER_ADDED,name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }
        this.gunRepository.add(gun);
        return String.format(ConstantMessages.GUN_ADDED,name,type);
    }

    @Override
    public String addGunToPlayer(String name) {
        List<Gun> guns = (List<Gun>) this.gunRepository.getModels();
        if (this.gunRepository.getModels().isEmpty()) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        else if (name.equals("Vercetti")) {
            Gun gun = guns.get(0);
            this.mainPlayer.getGunRepository().add(gun);
           this.gunRepository.remove(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER,gun.getName(),getMainPlayer().getName());
        }
        else if (this.players.containsKey(name)) {
            Gun gun = guns.get(0);
            this.players.get(name).getGunRepository().add(guns.get(0));
            this.gunRepository.remove(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER,gun.getName(),name);
        } else {
            return String.format(ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST);
        }

    }

    @Override
    public String fight() {
        this.neighbourhood.action(this.mainPlayer,this.players.values());
        boolean isOK = fightNotHappened(this.mainPlayer,this.players);
        if (isOK) {
            return String.format(ConstantMessages.FIGHT_HOT_HAPPENED);
        }

        int countDead = 0;
        int countLive = 0;
        for (Player player : this.players.values()) {
            if (player.isAlive()) {
                countLive++;
            } else {
                countDead++;
            }
        }


        StringBuilder sb = new StringBuilder();
        sb.append(ConstantMessages.FIGHT_HAPPENED).append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE,this.mainPlayer.getLifePoints())).append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,countDead)).append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE,countLive)).append(System.lineSeparator());
        return sb.toString().trim();
    }

    private boolean fightNotHappened(Player mainPlayer, Map<String, Player> players) {
        if (mainPlayer.getLifePoints() == 100) {
            for (Map.Entry<String, Player> stringPlayerEntry : players.entrySet()) {
                if (stringPlayerEntry.getValue().getLifePoints() != 50) {
                    return false;
                }
            }
            return true;

        } else {
            return false;
        }



    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

}
