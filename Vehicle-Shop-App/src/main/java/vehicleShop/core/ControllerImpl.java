package vehicleShop.core;

import vehicleShop.common.ConstantMessages;
import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private WorkerRepository workerRepository; // място за съхранение на работници
    private VehicleRepository vehicleRepository; // място за съхраниениа на превозно средство
         private int countMadeVehicle;

    //конструктор


    public ControllerImpl() {
        this.workerRepository = new WorkerRepository();
        this.vehicleRepository = new VehicleRepository();
        this.countMadeVehicle = 0;
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        //type - вид на работника (FirstShift || SecondShift)

        if (type.equals("FirstShift")) {
            worker = new FirstShift(workerName);
        } else if (type.equals("SecondShift")) {
            worker = new SecondShift(workerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }
        workerRepository.add(worker); // съхранияваме създаденият работник
        return String.format(ConstantMessages.ADDED_WORKER,type,workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName,strengthRequired);
        vehicleRepository.add(vehicle);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE,vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
         Worker worker = workerRepository.findByName(workerName);
         if (worker == null) {
             // не сме намерили работник
             throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
         }
        Tool tool = new ToolImpl(power); // създаваме инструмент със сила
        worker.addTool(tool);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER,power,workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> availableWorkers = workerRepository.getWorkers().stream()
                .filter(worker -> worker.getStrength() > 70)
                .collect(Collectors.toList()); // списък с валидни работници
        if (availableWorkers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }
        //намираме превозното средтсво от името
        Vehicle vehicle = vehicleRepository.findByName(vehicleName);
        Shop shop = new ShopImpl();
        int brokenTools = 0; // schupeni instrumenti
        while (!availableWorkers.isEmpty() && !vehicle.reached()) {
            Worker worker = availableWorkers.get(0);
            shop.make(vehicle,worker);
            brokenTools += worker.getTools().stream().filter(Tool::isUnfit).count();

            if (!worker.canWork() || worker.getTools().stream().noneMatch(t-> t.isUnfit())) {
                // ne moje da raboti
                availableWorkers.remove(worker);
            }
        }
        if (vehicle.reached()) {
           countMadeVehicle++;
           return String.format(ConstantMessages.VEHICLE_DONE,vehicle, "done") + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,brokenTools);
        } else {
            return String.format(ConstantMessages.VEHICLE_DONE,vehicle, "not done") + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,brokenTools);
        }
    }

    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!",countMadeVehicle)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());
        workerRepository.getWorkers()
                .forEach(worker -> {
                    sb.append(worker.toString()).append(System.lineSeparator());
                });
         return sb.toString().trim();
    }
}
