package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private Mission missions;
    private int count;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.missions = new MissionImpl();
        this.count = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut = null;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default: throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED,type,astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet =  new PlanetImpl(planetName);
        for (String it : items) {
            planet.getItems().add(it);
        }
        this.planetRepository.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED,planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut byName = this.astronautRepository.findByName(astronautName);
        if (byName == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST,astronautName));
        }
        this.astronautRepository.remove(byName);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED,astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = this.planetRepository.findByName(planetName);
        Collection<Astronaut> models = this.astronautRepository.getModels();
        List<Astronaut> astronautsMoreOxygen = new ArrayList<>();
        for (Astronaut astronaut : models) {
            if (astronaut.getOxygen() >= 60) {
                astronautsMoreOxygen.add(astronaut);
            }
        }
        if (astronautsMoreOxygen.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        this.missions.explore(planet,astronautsMoreOxygen);
        setCount(1);
        int countDeathAstronaut = 0;
        for (Astronaut as : astronautsMoreOxygen) {
            if (as.getOxygen() <= 0) {
                countDeathAstronaut++;
            }
        }

        return String.format(ConstantMessages.PLANET_EXPLORED,planetName,countDeathAstronaut);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d planets were explored!",getCount())).append(System.lineSeparator());
        sb.append("Astronauts info:").append(System.lineSeparator());
        for (Astronaut as : this.astronautRepository.getModels()) {
            sb.append(String.format("Name: %s",as.getName())).append(System.lineSeparator());
            sb.append(String.format("Oxygen: %.0f",as.getOxygen())).append(System.lineSeparator());
            sb.append("Bag items: ");
            if (as.getBag().getItems().isEmpty()) {
                sb.append("none");
            } else {
                as.getBag().getItems().forEach(i->sb.append(String.join(", ",i)));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = getCount() + 1;
    }
}
