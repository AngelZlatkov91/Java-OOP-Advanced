package org.BoxDeliver.PayBoxDelivered;

import org.BoxDeliver.TrackingNoumber.BaseTrackingNumber;
import org.BoxDeliver.TrackingNoumber.TrackingNumber;

public interface Pay {
    String getTypeClient();
    String getTypePay();

    double price(String trackingNumber, double getKilograms);
    void setService(boolean service);


}
