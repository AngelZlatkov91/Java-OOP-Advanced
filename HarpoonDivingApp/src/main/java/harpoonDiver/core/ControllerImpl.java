package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller{
    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;
    private DivingImpl diving;
    private int countDiveTook;



    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
        this.diving = new DivingImpl();
        this.countDiveTook = 0;
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver = null;
        switch (kind) {
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.DIVER_INVALID_KIND);
        }
        this.diverRepository.add(diver);
        return String.format(ConstantMessages.DIVER_ADDED,kind,diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        divingSite.getSeaCreatures().addAll(List.of(seaCreatures));

        this.divingSiteRepository.add(divingSite);
        return String.format(ConstantMessages.DIVING_SITE_ADDED,siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = this.diverRepository.byName(diverName);
        if (diver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DIVER_DOES_NOT_EXIST,diverName));
        }
        this.diverRepository.remove(diver);
        return String.format(ConstantMessages.DIVER_REMOVE,diverName);
    }

    @Override
    public String startDiving(String siteName) {
        DivingSite divingSite = this.divingSiteRepository.byName(siteName);
        List<Diver> suitableDivers = new ArrayList<>();
        this.diverRepository.getCollection().forEach(d-> {
            if (d.getOxygen() > 30) {
                suitableDivers.add(d);
            }
        });
        if (suitableDivers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SITE_DIVERS_DOES_NOT_EXISTS);
        }
        this.diving.searching(divingSite,suitableDivers);
        int countRemoved = 0;
        setCountDiveTook(getCountDiveTook() + 1);
        for (Diver diver : this.diverRepository.getCollection()) {
            if (!diver.canDive()) {
                countRemoved++;
            }
        }
        return String.format(ConstantMessages.SITE_DIVING,siteName,countRemoved);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("The dive took place at %d site/s.",getCountDiveTook())).append(System.lineSeparator());
        sb.append("Diver's statistics:").append(System.lineSeparator());
        for (Diver diver : this.diverRepository.getCollection()) {
            sb.append(String.format("Name: %s",diver.getName())).append(System.lineSeparator());
            sb.append(String.format("Oxygen: %.0f",diver.getOxygen())).append(System.lineSeparator());
            sb.append("Diver's catch: ");
            if (diver.getSeaCatch().getSeaCreatures().isEmpty()) {
                sb.append("None");
            } else {
                sb.append(String.join(", ",diver.getSeaCatch().getSeaCreatures()));
            }
            sb.append(System.lineSeparator());
        }
        //TODO
        return sb.toString().trim();
    }
    private int getCountDiveTook() {
        return countDiveTook;
    }

    private void setCountDiveTook(int countDiveTook) {
        this.countDiveTook = countDiveTook;
    }
}
