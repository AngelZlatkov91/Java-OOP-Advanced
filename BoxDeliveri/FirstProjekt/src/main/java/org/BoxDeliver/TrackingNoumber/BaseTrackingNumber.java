package org.BoxDeliver.TrackingNoumber;
import org.BoxDeliver.Deliver.BaseDeliver;
import org.BoxDeliver.PayBoxDelivered.BasePay;
import org.BoxDeliver.Sender.BaseSender;
import org.BoxDeliver.common.ExceptionMessages;


public class BaseTrackingNumber implements TrackingNumber{
    private String trackingNumber;
    private double kilograms;
    private BaseSender sender;
    private BaseDeliver deliver;
    private BasePay  basePay;

    public BaseTrackingNumber(String trackingNumber, double kilograms) {
        this.setTrackingNumber(trackingNumber);
        this.setKilograms(kilograms);
        this.sender = null;
        this.deliver = null;
        this.basePay = null;
    }

    @Override
    public BaseSender getSender() {
        return sender;
    }


    @Override
    public BaseDeliver getDeliver() {
        return deliver;
    }

    @Override
    public void addSender(BaseSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException(ExceptionMessages.TRACK_NUM_CANNOT_ADD_EMPTY_SENDER);
        }
        this.sender = sender;
    }

    @Override
    public void addDeliver(BaseDeliver deliver) {
        if (deliver == null) {
            throw new IllegalArgumentException(ExceptionMessages.TRACK_NUM_CANNOT_ADD_EMPTY_DELIVER);
        }
           this.deliver = deliver;
    }

    @Override
    public BasePay getBasePay() {
        return this.basePay;
    }


    @Override
    public double price() {
        return this.basePay.price(getTrackingNumber(),getKilograms());
    }

    @Override
    public void addBasePay(String typeClient, String typePay) {
        if (this.sender == null || this.deliver == null) {
            throw new IllegalArgumentException(ExceptionMessages.TRACKING_NUMBER_NOT_HAVE_ADDED_DELIVER_AND_SENDER);
        }
        this.basePay = new BasePay(typeClient,typePay);
    }

    @Override
    public void addBasePay(String typeClient, String typePay, String moneyService) {
        if (this.sender == null || this.deliver == null) {
            throw new IllegalArgumentException(ExceptionMessages.TRACKING_NUMBER_NOT_HAVE_ADDED_DELIVER_AND_SENDER);
        }
        BasePay pay = new BasePay(typeClient,typePay);
        pay.setService(true);
        this.basePay = pay;
    }


    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        if (trackingNumber == null || trackingNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.TRACK_NUM_CANNOT_BE_NULL);
        }
        this.trackingNumber = trackingNumber;
    }

    public double getKilograms() {
        return kilograms;
    }


    public void setKilograms(double kilograms) {
        if (kilograms < 0) {
            throw new IllegalArgumentException(ExceptionMessages.KILOGRAMS_CANNOT_BE_NEGATIVE);
        }
        this.kilograms = kilograms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tracking number is: ").append(getTrackingNumber()).append(System.lineSeparator());

        sb.append(getSender().toString()).append(System.lineSeparator());

        sb.append(getDeliver().toString()).append(System.lineSeparator());

        sb.append("This Box is ").append(String.format("%.2f",getKilograms())).append(" kg.").append(System.lineSeparator());
        sb.append("Price for a Box is ").append(String.format("%.2f",this.price())).append(" euro.").append(System.lineSeparator());
        sb.append("For delivery service pay - ").append(this.basePay.getTypeClient()).append(" with ").append(this.basePay.getTypePay()).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
