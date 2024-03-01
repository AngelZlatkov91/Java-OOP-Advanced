package magicGame.models.region;
import magicGame.models.magicians.Magician;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegionImpl implements Region{
    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = new ArrayList<>();
        List<Magician> blackWidows = new ArrayList<>();
        for (Magician m : magicians) {
            if (m.getClass().getSimpleName().equals("Wizard")) {
                wizards.add(m);
            } else if (m.getClass().getSimpleName().equals("BlackWidow"))  {
                blackWidows.add(m);
            }
        }
        int countMovie = 1;
        String win = "Black widows win!";
        while (!wizards.isEmpty()) {
            if (blackWidows.isEmpty()) {
                win = "Wizards win!";
                break;
            }
            if (countMovie % 2 == 0) {
                Magician firstWizard = wizards.get(0);
                Magician firstBlackWidow = blackWidows.get(0);
                int fire = firstBlackWidow.getMagic().fire();
                if (fire > 0) {
                    firstWizard.takeDamage(fire);
                    if (!firstWizard.isAlive()) {
                        wizards.remove(0);

                    }
                }
                countMovie++;
            } else {
                Magician firstWizard = wizards.get(0);
                Magician firstBlackWidow = blackWidows.get(0);
                int fire = firstWizard.getMagic().fire();
                if (fire > 0) {
                    firstBlackWidow.takeDamage(fire);
                    if (!firstBlackWidow.isAlive()) {
                        blackWidows.remove(0);
                    }
                }
                countMovie++;
            }
        }
        return win;
    }
}
