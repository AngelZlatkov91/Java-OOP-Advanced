package football.repositories;

import football.entities.supplement.Supplement;

public interface Repository {

    void add(Supplement supplement);
    boolean remove(Supplement supplement);
    Supplement findByType(String type);



}
