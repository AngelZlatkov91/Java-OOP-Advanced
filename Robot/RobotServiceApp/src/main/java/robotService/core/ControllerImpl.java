package robotService.core;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Collection<Service> services;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        switch (type){
            case "MainService":
                service = new MainService(name);
                break;
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }
        this.services.add(service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE,type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        switch (type){
            case"PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);

        }
        this.supplementRepository.addSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = this.supplementRepository.findFirst(supplementType);
        Service service = this.services.stream()
                .filter(s-> serviceName.equals(s.getName()))
                .findFirst()
                .orElse(null);
        if (supplement == null || service == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND,supplementType));
        }
        this.supplementRepository.removeSupplement(supplement);
        service.addSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,supplementType);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        switch (robotType){
            case "MaleRobot":
                robot = new MaleRobot(robotName,robotKind,price);
                break;
            case"FemaleRobot":
                robot = new FemaleRobot(robotName,robotKind,price);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_ROBOT_TYPE);
        }
        Service service = this.services.stream()
                .filter(s->serviceName.equals(s.getName()))
                .findFirst()
                .orElse(null);
        try {
            if (service!= null) {
                service.addRobot(robot);
            }
        } catch (IllegalArgumentException ex){
            return ConstantMessages.UNSUITABLE_SERVICE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE,robotType,serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = this.services.stream()
                .filter(e->serviceName.equals(e.getName()))
                .findFirst()
                .orElse(null);
        if (service != null) {
            service.feeding();
            return String.format(ConstantMessages.FEEDING_ROBOT,service.getRobots().size());
        }
        return null;
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = this.services.stream()
                .filter(s->serviceName.equals(s.getName()))
                .findFirst()
                .orElse(null);
        if (service != null) {
            double robotPrice = service.getRobots().stream().mapToDouble(Robot::getPrice).sum();
            double suplementPrice = service.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();
            return String.format(ConstantMessages.VALUE_SERVICE,serviceName,robotPrice+suplementPrice);
        }
        return null;
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.services.forEach(e-> {
            sb.append(e.getStatistics());
            sb.append(System.lineSeparator());
        });
        return sb.toString().trim();
    }
}
