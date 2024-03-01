package org.BoxDeliver.TrackingNoumber;
import org.BoxDeliver.Deliver.BaseDeliver;
import org.BoxDeliver.PayBoxDelivered.BasePay;
import org.BoxDeliver.Sender.BaseSender;

public interface TrackingNumber {
    String getTrackingNumber();
    double getKilograms();
    BaseSender getSender();
    BaseDeliver getDeliver();
     void addSender(BaseSender sender);
     void  addDeliver(BaseDeliver deliver);
     BasePay getBasePay();
     double price();

    void addBasePay(String typeClient, String typePay);
    void addBasePay(String typeClient, String typePay,String moneyService);
}
