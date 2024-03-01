package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.*;

public class GunRepository implements Repository<Gun>{
    private Collection<Gun> guns;

    public GunRepository() {
        this.guns = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.guns;
    }

    @Override
    public void add(Gun model) {
          if (!this.guns.contains(model)) {
              this.guns.add(model);
          }
    }

    @Override
    public boolean remove(Gun model) {
        return this.guns.remove(model);
    }

    @Override
    public Gun find(String name) {
        return this.guns
                .stream()
                .filter(g->name.equals(g.getName()))
                .findFirst()
                .orElse(null);
    }
}
