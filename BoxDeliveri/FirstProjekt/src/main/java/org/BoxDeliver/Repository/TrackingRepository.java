package org.BoxDeliver.Repository;

import org.BoxDeliver.TrackingNoumber.TrackingNumber;
import org.BoxDeliver.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrackingRepository implements RepositoryTrackingNumber{
    private Map<String, TrackingNumber> trackingNumberMap;

    public TrackingRepository() {
        this.trackingNumberMap = new LinkedHashMap<>();
    }

    @Override
    public void add(TrackingNumber trackingNumber) {
        String trackingNumber1 = trackingNumber.getTrackingNumber();
        if (this.trackingNumberMap.containsKey(trackingNumber1)) {
            throw new IllegalArgumentException(ExceptionMessages.TRACKING_NUMBER_IS_EXIST);
        }
        this.trackingNumberMap.put(trackingNumber1,trackingNumber);
    }

    @Override
    public TrackingNumber findFirst(String trackingNumber) {
        if (!this.trackingNumberMap.containsKey(trackingNumber)) {
            throw new IllegalArgumentException(ExceptionMessages.TRACKING_NUMBER_IS_NOT_EXIST);
        }
        return this.trackingNumberMap.get(trackingNumber);
    }

    @Override
    public boolean isDelivered(String trackingNumber) {
        if (this.trackingNumberMap.containsKey(trackingNumber)) {
            this.trackingNumberMap.remove(trackingNumber);
            return true;
        }
        return false;
    }

    @Override
    public boolean isContains(String trackingNumber) {
        if (this.trackingNumberMap.containsKey(trackingNumber)) {
            return true;
        }
        return false;
    }

    @Override
    public List<TrackingNumber> trackingNumbers() {
        List<TrackingNumber> trackingNumbers = new ArrayList<>();
        if (this.trackingNumberMap.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_TRACKING_NUMBER_LIST);
        }
        for (Map.Entry<String, TrackingNumber> stringTrackingNumberEntry : this.trackingNumberMap.entrySet()) {
            trackingNumbers.add(stringTrackingNumberEntry.getValue());
        }
        return trackingNumbers;
    }

    @Override
    public int getCount() {
        return this.trackingNumberMap.size();
    }
}
