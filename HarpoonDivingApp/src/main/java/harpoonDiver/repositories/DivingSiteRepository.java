package harpoonDiver.repositories;

import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DivingSiteRepository implements Repository<DivingSite>{
    private Map<String, DivingSite> sites;

    public DivingSiteRepository() {
        this.sites = new LinkedHashMap<>();
    }

    @Override
    public Collection<DivingSite> getCollection() {
        return Collections.unmodifiableCollection(this.sites.values());
    }

    @Override
    public void add(DivingSite entity) {
       if (!this.sites.containsKey(entity.getName())) {
           this.sites.put(entity.getName(),entity);
       }
    }

    @Override
    public boolean remove(DivingSite entity) {
        if (this.sites.containsKey(entity.getName())) {
            this.sites.remove(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    public DivingSite byName(String name) {
        return this.sites.get(name);
    }
}
