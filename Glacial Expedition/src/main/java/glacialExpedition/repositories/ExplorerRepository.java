package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.ArrayList;
import java.util.Collection;

public class ExplorerRepository implements Repository<Explorer>{
    private Collection<Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return this.explorers;
    }

    @Override
    public void add(Explorer entity) {
         if (!this.explorers.contains(entity)) {
             this.explorers.add(entity);
         }
    }

    @Override
    public boolean remove(Explorer entity) {
        return this.explorers.remove(entity);
    }

    @Override
    public Explorer byName(String name) {
        return this.explorers
                .stream().filter(e-> name.equals(e.getName()))
                .findFirst()
                .orElse(null);
    }
}
