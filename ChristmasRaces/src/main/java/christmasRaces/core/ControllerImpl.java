package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.*;

public class ControllerImpl implements Controller {
    private DriverRepository driverRepository;
    private CarRepository carRepository;
    private RaceRepository raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = new DriverRepository();
        this.carRepository = new CarRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String driver) {
        Driver byName = this.driverRepository.getByName(driver);
        if (byName != null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,driver));
        }
        Driver driver1 = new DriverImpl(driver);
        this.driverRepository.add(driver1);
        return String.format(OutputMessages.DRIVER_CREATED,driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = null ;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model,horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
        }
        Car byName = this.carRepository.getByName(model);
        if (byName != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS,model));
        }
        this.carRepository.add(car);
        assert car != null;
        return String.format(OutputMessages.CAR_CREATED, car.getClass().getSimpleName(), model);

    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.driverRepository.getByName(driverName);
        Car car = this.carRepository.getByName(carModel);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));
        }
        if (car == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND,carModel));
        }
            driver.addCar(car);
        return String.format(OutputMessages.CAR_ADDED,driver.getName(),car.getModel());
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.raceRepository.getByName(raceName);
        Driver driver = this.driverRepository.getByName(driverName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));
        }
        race.addDriver(driver);
        return String.format(OutputMessages.DRIVER_ADDED,driverName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }
        Collection<Driver> drivers = race.getDrivers();
        if (drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName));
        }
        Map<Double,Driver> driverMap = new TreeMap<>();
        for (Driver driver : drivers) {
            double points = driver.getCar().calculateRacePoints(race.getLaps());
            driverMap.put(points,driver);
        }
        this.raceRepository.remove(race);
        List<Driver> driver = new ArrayList<>();
        driverMap.forEach((k,v) -> driver.add(v));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION,driver.get(driver.size()-1).getName(),race.getName())).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION,driver.get(driver.size()-2).getName(),race.getName())).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION,driver.get(driver.size()-3).getName(),race.getName())).append(System.lineSeparator());
        Driver first = this.driverRepository.getByName(driver.get(driver.size() - 1).getName());
        first.winRace();
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race byName = this.raceRepository.getByName(name);
        if (byName != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,name));
        }
        Race race = new RaceImpl(name,laps);
        this.raceRepository.add(race);
        return null;
    }
}
