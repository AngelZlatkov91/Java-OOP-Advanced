package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DivingImpl implements Diving{
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
        List<Diver> diverList = new ArrayList<>();
        for (Diver diver : divers) {
                diverList.add(diver);
        }
        List<String> divingSites = new ArrayList<>();
        for (String diving : divingSite.getSeaCreatures()) {
            divingSites.add(diving);
        }
        for (Diver actionDiver : diverList) {
            while (actionDiver.canDive()) {
                if (divingSites.isEmpty()) {
                    break;
                }
                String fish = divingSites.get(0);
                actionDiver.getSeaCatch().getSeaCreatures().add(fish);
                actionDiver.shoot();
                divingSites.remove(0);
            }
            if (divingSites.isEmpty()) {
                break;
            }

        }
    }
}
