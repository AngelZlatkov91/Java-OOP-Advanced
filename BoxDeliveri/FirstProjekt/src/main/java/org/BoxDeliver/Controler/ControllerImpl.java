package org.BoxDeliver.Controler;
import org.BoxDeliver.Deliver.BaseDeliver;
import org.BoxDeliver.Deliver.Deliver;
import org.BoxDeliver.Repository.SenderRepository;
import org.BoxDeliver.Repository.TrackingRepository;
import org.BoxDeliver.Sender.BaseSender;
import org.BoxDeliver.Sender.Sender;
import org.BoxDeliver.TrackingNoumber.BaseTrackingNumber;
import org.BoxDeliver.TrackingNoumber.TrackingNumber;
import org.BoxDeliver.common.ConstantMessages;
import org.BoxDeliver.common.ExceptionMessages;

import java.util.List;

public class ControllerImpl implements Controller{
    private TrackingRepository trackingRepository;
    private SenderRepository senderRepository;

    public ControllerImpl() {
        this.trackingRepository = new TrackingRepository();
        this.senderRepository = new SenderRepository();
    }

    @Override
    public String addTrackingNumber(String trackingNumber, double kilograms) {
        BaseTrackingNumber trackingNumber1 = new BaseTrackingNumber(trackingNumber,kilograms);
       this.trackingRepository.add(trackingNumber1);
        return String.format(ConstantMessages.TRACKING_NUMBER_IS_SUCCESS_ADDED,trackingNumber);
    }

    @Override
    public String addSender(String trackingNumber,String name, String town, String address, String phone) {
        TrackingNumber first = this.trackingRepository.findFirst(trackingNumber);
        BaseSender sender = new BaseSender(name,town,address,phone);
        if (first.getSender() != null) {
            throw new IllegalArgumentException(ExceptionMessages.TRACKING_NUMBER_HAS_ADDED_SENDER);
        }
        first.addSender(sender);
        if (!this.senderRepository.isHere(name)) {
            this.senderRepository.add(sender);
        }
        return ConstantMessages.SUCCESS_ADDED_SENDER;
    }

    @Override
    public String addDeliver(String trackingNumber,String name, String town,String postCode, String address, String phone) {
        TrackingNumber first = this.trackingRepository.findFirst(trackingNumber);
        BaseDeliver deliver = new BaseDeliver(name, town,postCode, address, phone);
        if (first.getDeliver() != null) {
            throw new IllegalArgumentException(ExceptionMessages.TRACKING_NUMBER_HAS_ADDED_DELIVER);
        }
        first.addDeliver(deliver);
        return ConstantMessages.SUCCESS_ADDED_DELIVER;
    }

    @Override
    public String findTrackingNumber(String trackingNumber) {
        return this.trackingRepository.findFirst(trackingNumber).toString();
    }

    @Override
    public boolean isDelivered(TrackingNumber trackingNumber) {
        return this.trackingRepository.isDelivered(trackingNumber.getTrackingNumber());
    }

    @Override
    public Sender findSender(String name) {
        return this.senderRepository.findFirst(name);
    }

    @Override
    public double allSum() {
        double sum = 0;
        List<TrackingNumber> trackingNumbers = this.trackingRepository.trackingNumbers();
        for (TrackingNumber tr : trackingNumbers) {
            sum +=tr.price();
        }
        return sum;
    }


    @Override
    public String price(String trackingNumber) {
        TrackingNumber first = this.trackingRepository.findFirst(trackingNumber);
        String clientPay = first.getBasePay().getTypeClient();
        String payment = first.getBasePay().getTypePay();
        return String.format("The price for Box is %.2f pay %s with %s.",first.price(),clientPay,payment);
    }

    @Override
    public String getAll() {
        StringBuilder sb = new StringBuilder();
        List<TrackingNumber> trackingNumbers = this.trackingRepository.trackingNumbers();
        trackingNumbers.forEach(t->sb.append(t.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }

    @Override
    public Deliver changeDataDeliver(String trackingNumber) {
        TrackingNumber first = this.trackingRepository.findFirst(trackingNumber);
        return first.getDeliver();
    }

    @Override
    public void addPay(String trackingNumber,String typeClient, String typePay) {
        TrackingNumber first = this.trackingRepository.findFirst(trackingNumber);
        first.addBasePay(typeClient,typePay);
    }

    @Override
    public void addPay(String trackingNumber, String typeClient, String typePay, String servicePay) {
        TrackingNumber first = this.trackingRepository.findFirst(trackingNumber);
        first.addBasePay(typeClient,typePay);
    }
}
