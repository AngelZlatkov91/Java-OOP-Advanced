package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        List<String> tokens = (List<String>) state.getExhibits();
        for (Explorer explorer : explorers) {
            if (explorer.canSearch()) {
                for (int i = 0; i < tokens.size(); i++) {
                    if (explorer.canSearch()) {
                        explorer.getSuitcase().getExhibits().add(tokens.get(0));
                        explorer.search();
                        tokens.remove(0);
                    } else {
                        break;
                    }

                }
            }
        }
    }
}
