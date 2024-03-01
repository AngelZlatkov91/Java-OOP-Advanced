package magicGame.repositories.interfaces;
import magicGame.common.ExceptionMessages;
import magicGame.models.magicians.Magician;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MagicianRepositoryImpl implements MagicianRepository<Magician>{
    private Collection<Magician> magicians;

    public MagicianRepositoryImpl() {
        this.magicians = new ArrayList<>();
    }

    @Override
    public Collection<Magician> getData() {
        return magicians;
    }

    @Override
    public void addMagician(Magician model) {
       if (model == null) {
           throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_REPOSITORY);

       }
       this.magicians.add(model);
    }

    @Override
    public boolean removeMagician(Magician model) {
        return this.magicians.remove(model);
    }

    @Override
    public Magician findByUsername(String name) {
        return this.magicians
                .stream()
                .filter(ma->name.equals(ma.getUsername()))
                .findFirst()
                .orElse(null);
    }
}
