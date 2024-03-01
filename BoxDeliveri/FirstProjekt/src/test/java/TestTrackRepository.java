import org.BoxDeliver.Deliver.BaseDeliver;
import org.BoxDeliver.Repository.TrackingRepository;
import org.BoxDeliver.Sender.BaseSender;
import org.BoxDeliver.TrackingNoumber.BaseTrackingNumber;
import org.BoxDeliver.TrackingNoumber.TrackingNumber;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestTrackRepository {
    @Test(expected = IllegalArgumentException.class)
    public void testAddTrackContains(){
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","132", " ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        trackingRepository.add(baseTrackingNumber);
    }
    @Test
    public void testAddCorrect() {
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        Assert.assertEquals(trackingRepository.getCount(),1);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testFindFirstThrow() {
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        trackingRepository.findFirst("dsadasdasd");
    }
    @Test
    public void testFindFirst() {
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","132", " ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        TrackingNumber first = trackingRepository.findFirst("132465879");
        Assert.assertEquals(first,baseTrackingNumber);
    }
    @Test
    public void testIsDeliveredFalse(){
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        boolean asdasdasd = trackingRepository.isDelivered("asdasdasd");
        Assert.assertFalse(asdasdasd);
    }
    @Test
    public void testIsDeliveredTrue(){
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        boolean isDeliver = trackingRepository.isDelivered("132465879");
        Assert.assertTrue(isDeliver);
        Assert.assertEquals(trackingRepository.getCount(),0);
    }
    @Test
    public void testIsContainsFalse(){
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        boolean contains = trackingRepository.isContains("132465");
        Assert.assertFalse(contains);
    }
    @Test
    public void testIsContainsTrue(){
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        boolean contains = trackingRepository.isContains("132465879");
        Assert.assertTrue(contains);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testTrackEmptyRepo(){
        TrackingRepository trackingRepository = new TrackingRepository();
        List<TrackingNumber> trackingNumbers = trackingRepository.trackingNumbers();
    }
    @Test
    public void testTrackNumbers(){
        TrackingRepository trackingRepository = new TrackingRepository();
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","132", " ","056545");
        baseTrackingNumber.addDeliver(deliver);
        trackingRepository.add(baseTrackingNumber);
        List<TrackingNumber> trackingNumbers = trackingRepository.trackingNumbers();
        TrackingNumber trackingNumber = trackingNumbers.get(0);
        Assert.assertEquals(trackingNumber,baseTrackingNumber);
    }








}
