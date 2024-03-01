package spaceStation.models.bags;

import spaceStation.models.mission.Mission;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public interface Bag extends Mission, Planet {
    Collection<String> getItems();


}
