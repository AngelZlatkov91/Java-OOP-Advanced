package harpoonDiver.repositories;

import harpoonDiver.models.diver.Diver;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiverRepository implements Repository<Diver>{
    private Map<String,Diver> divers;

    public DiverRepository() {
        this.divers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Diver> getCollection() {
        return Collections.unmodifiableCollection(this.divers.values());
    }

    @Override
    public void add(Diver entity) {
     if (!this.divers.containsKey(entity.getName())) {
         this.divers.put(entity.getName(), entity);
     }
    }

    @Override
    public boolean remove(Diver entity) {
        if (this.divers.containsKey(entity.getName())) {
            this.divers.remove(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    public Diver byName(String name) {
        return this.divers.get(name);
    }
}
