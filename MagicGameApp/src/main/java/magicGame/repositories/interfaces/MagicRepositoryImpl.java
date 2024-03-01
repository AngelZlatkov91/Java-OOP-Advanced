package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MagicRepositoryImpl implements MagicRepository<Magic>{
    private Collection<Magic> magics;

    public MagicRepositoryImpl() {
        this.magics = new ArrayList<>();
    }

    @Override
    public Collection<Magic> getData() {
        return magics;
    }

    @Override
    public void addMagic(Magic model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_REPOSITORY);
        }
           this.magics.add(model);
    }

    @Override
    public boolean removeMagic(Magic model) {
        return this.magics.remove(model);
    }

    @Override
    public Magic findByName(String name) {
        return this.magics
                .stream()
                .filter(m-> name.equals(m.getName()))
                .findFirst()
                .orElse(null);
    }
}
