package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private Mission mission;
    private int countState;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.mission = new MissionImpl();
          this.countState = 0;

    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer = null;
        switch (type) {
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        this.explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String tokens : exhibits) {
            state.getExhibits().add(tokens);
        }
        this.stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = this.explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        this.explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        State state = this.stateRepository.byName(stateName);
        List<Explorer> canExplorer = new ArrayList<>();
        for (Explorer explorer : this.explorerRepository.getCollection()) {
            if (explorer.getEnergy() > 50) {
                canExplorer.add(explorer);
            }
        }
        if (canExplorer.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        this.mission.explore(state, canExplorer);
        setCountState(getCountState() + 1);
        int countRetired = 0;
        for (Explorer e : canExplorer) {
            if (!e.canSearch()) {
                countRetired++;
            }
        }
        return String.format(ConstantMessages.STATE_EXPLORER,stateName,countRetired);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d states were explored.",getCountState())).append(System.lineSeparator());
        sb.append("Information for the explorers:").append(System.lineSeparator());
        for (Explorer explorer : this.explorerRepository.getCollection()) {
            sb.append(String.format("Name: %s",explorer.getName())).append(System.lineSeparator());
            sb.append(String.format("Energy: %.0f",explorer.getEnergy())).append(System.lineSeparator());
            sb.append("Suitcase exhibits: ");
            if (explorer.getSuitcase().getExhibits().isEmpty()) {
                sb.append("None");
            } else {
                sb.append(String.format(String.join(", ",explorer.getSuitcase().getExhibits())));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private int getCountState() {
        return countState;
    }

    private void setCountState(int countState) {
        this.countState = countState;
    }
}
