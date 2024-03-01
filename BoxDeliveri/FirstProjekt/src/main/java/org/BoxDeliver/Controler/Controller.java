package org.BoxDeliver.Controler;

import org.BoxDeliver.Deliver.Deliver;
import org.BoxDeliver.Sender.Sender;
import org.BoxDeliver.TrackingNoumber.TrackingNumber;

public interface Controller {
    String addTrackingNumber(String trackingNumber, double kilograms);
    String addSender(String trackingNumber,String name,String town,String address,String phone);
    String addDeliver(String trackingNumber,String name,String postCode,String town,String address,String phone);
    String findTrackingNumber(String trackingNumber);
    boolean isDelivered(TrackingNumber trackingNumber);
    Sender findSender(String name);
    double allSum();
    String price(String trackingNumber);
    String getAll();
    Deliver changeDataDeliver(String trackingNumber);
    void addPay(String trackingNumber,String typeClient,String typePay);
    void addPay(String trackingNumber,String typeClient,String typePay,String servicePay);

}
