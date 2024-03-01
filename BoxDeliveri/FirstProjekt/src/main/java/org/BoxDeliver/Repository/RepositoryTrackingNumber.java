package org.BoxDeliver.Repository;

import org.BoxDeliver.TrackingNoumber.TrackingNumber;

import java.util.List;

public interface RepositoryTrackingNumber {

    void add(TrackingNumber trackingNumber);
    TrackingNumber findFirst(String trackingNumber);
    boolean isDelivered(String trackingNumber);
    boolean isContains(String trackingNumber);
    List<TrackingNumber> trackingNumbers();

    int getCount();



}
