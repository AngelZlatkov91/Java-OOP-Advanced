package org.BoxDeliver.Controler;
import org.BoxDeliver.Deliver.Deliver;
import org.BoxDeliver.Sender.Sender;
import org.BoxDeliver.common.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EngineImpl implements Engine{
    private ControllerImpl controller;
    private BufferedReader reader;

    public EngineImpl() {
        this.controller = new ControllerImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();
                if (result.equals("End")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException e) {
                result = e.getMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String [] data = input.split(",");
        Command command = Command.valueOf(data[0]);
        String result = null;
        switch (command){
            case NewNumber:
                result = newNumber(data);
                break;
            case SenderData:
                result = addSender(data);
                break;
            case DeliverData:
                result = getDeliver(data);
                break;
            case Payment:
                result = getPriceForBox(data);
                break;
            case FindTRackingNumber:
                result = getTrackingNumber(data);
                break;
            case FindSender:
                result = getSender(data).toString();
                break;
            case ChangeDeliverData:
                result = changeDataDeliver(data).toString();
                break;
            case AllSum:
                result = allSumOfBoxes();
                break;
            case GetAll:
                result = allBoxes();
                break;
            case End:
                result = "End";
                break;

        }
        return result;
    }

    private Deliver changeDataDeliver(String[] data) {
        Deliver deliver = this.controller.changeDataDeliver(data[1]);
        String type = data[2];
        String changeData = data[3];
        switch (type){
            case "Name":
                deliver.setName(changeData);
                break;
            case "Town":
                deliver.setTown(changeData);
                break;
            case "Address":
                deliver.setAddress(changeData);
                break;
            case "Phone":
                deliver.setPhone(changeData);
                break;
        }
        return deliver;
    }

    private String allBoxes() {
        return controller.getAll();
    }

    private String allSumOfBoxes() {
        return String.format("All sum for all Boxes is %.2f", controller.allSum());
    }

    private Sender getSender(String[] data) {
        Sender sender = controller.findSender(data[1]);
        String type = data[2];
        String setData = data[3];
        switch (type){
            case "Name":
                sender.setName(setData);
            case "Town":
                sender.setTown(setData);
                break;
            case "Address":
                sender.setAddress(setData);
                break;
            case "Phone":
                sender.setPhone(setData);
                break;
        }
        return sender;
    }

    private String getTrackingNumber(String[] data) {
        return controller.findTrackingNumber(data[1]);
    }

    private String getPriceForBox(String[] data) {
        if (data.length ==5) {
            controller.addPay(data[1],data[2],data[3],data[4]);
        }
        controller.addPay(data[1],data[2],data[3]);

        return controller.price(data[1]);
    }

    private String getDeliver(String[] data) {
        return controller.addDeliver(data[1], data[2], data[3], data[4], data[5],data[6]);
    }

    private String addSender(String[] data) {
        return controller.addSender(data[1], data[2], data[3], data[4], data[5]);
    }

    private String newNumber(String[] data) {
        return controller.addTrackingNumber(data[1], Double.parseDouble(data[2]));
    }
}
