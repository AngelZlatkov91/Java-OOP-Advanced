import org.BoxDeliver.Deliver.BaseDeliver;
import org.BoxDeliver.PayBoxDelivered.BasePay;
import org.BoxDeliver.Sender.BaseSender;
import org.BoxDeliver.TrackingNoumber.BaseTrackingNumber;
import org.junit.Assert;
import org.junit.Test;

public class TestBaseTrackingNumber {

     @Test(expected = IllegalArgumentException.class)
    public void testCreateTrackingNumThrowWithName(){
         BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber(null,50.0);
     }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTrackingNumThrowWithKilograms(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("02546541354",-5);
    }
    @Test
    public void testCreateTrackingNumCorrect(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        String trackingNum = baseTrackingNumber.getTrackingNumber();
        Assert.assertEquals(trackingNum,"132465879");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullSenderInTrackNumThrow() {
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = null;
        baseTrackingNumber.addSender(sender);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullDeliverInTrackNumThrow() {
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseDeliver deliver = null;
        baseTrackingNumber.addDeliver(deliver);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddSenderIncorrectDataToTrackNum() {
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender(null,"Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
    }
    @Test
    public void testAddSenderInTrackNum() {
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseSender sender1 = baseTrackingNumber.getSender();
        Assert.assertEquals(sender1,sender);
    }
    @Test
    public void testAddDeliverInTrackNum() {
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","132", " ","056545");
        baseTrackingNumber.addDeliver(deliver);
        BaseDeliver deliver1 = baseTrackingNumber.getDeliver();
        Assert.assertEquals(deliver,deliver1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddPayWithNullSenderAndDeliver(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("465654465",50.0);
        baseTrackingNumber.addBasePay("Sender","Cash");
    }
    @Test
    public void testAddPayInTrackNumCorrect() {
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        baseTrackingNumber.addBasePay("Sender","Cash");
        BasePay basePay = baseTrackingNumber.getBasePay();
        String typePay = basePay.getTypePay();
        String typeClient = basePay.getTypeClient();
        Assert.assertEquals(typePay,"Cash");
        Assert.assertEquals(typeClient,"Sender");
    }
    @Test
    public void testPriceForBox(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",4);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        baseTrackingNumber.addBasePay("Sender","Cash");
        String format = String.format("%.2f leva",baseTrackingNumber.price());
        String current = "11,30 leva";
        Assert.assertEquals(format,current);
    }
    @Test
    public void testPriceWithAm(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",4);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        baseTrackingNumber.addBasePay("Sender","Cash");
        BasePay basePay = baseTrackingNumber.getBasePay();
        basePay.setService(true);
        String format = String.format("%.2f leva",baseTrackingNumber.price());
        String current = "13,30 leva";
        Assert.assertEquals(format,current);
    }
    @Test (expected = NullPointerException.class)
    public void testOnlyAm(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",50.0);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","132", " ","056545");
        baseTrackingNumber.addDeliver(deliver);
        BasePay basePay = baseTrackingNumber.getBasePay();
        basePay.setService(true);
        baseTrackingNumber.addBasePay("Sender","Cash");
        String format = String.format("%.2f leva",baseTrackingNumber.price());
        String current = "13,30 leva";
        Assert.assertEquals(format,current);
    }
    @Test
    public void testAddPayWithAmMoneyService() {
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",4);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","132", " ","056545");
        baseTrackingNumber.addDeliver(deliver);
        baseTrackingNumber.addBasePay("Sender","Cash","am");
        String format = String.format("%.2f leva",baseTrackingNumber.price());
        String current = "13,30 leva";
        Assert.assertEquals(format,current);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddPayWithNullSenderAndDeliverInMoneyService(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("465654465",50.0);
        baseTrackingNumber.addBasePay("Sender","Cash","am");
    }
    @Test
    public  void testStringInTrackingNum(){
        BaseTrackingNumber baseTrackingNumber = new BaseTrackingNumber("132465879",4);
        BaseSender sender = new BaseSender("Angel","Petrich", " ","056545");
        baseTrackingNumber.addSender(sender);
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich", "132"," ","056545");
        baseTrackingNumber.addDeliver(deliver);
        baseTrackingNumber.addBasePay("Sender","Cash","am");
        StringBuilder sb = new StringBuilder();
        sb.append("Tracking number is: 132465879").append(System.lineSeparator());
        sb.append(baseTrackingNumber.getSender().toString()).append(System.lineSeparator());
        sb.append(baseTrackingNumber.getDeliver().toString()).append(System.lineSeparator());
        sb.append("This Box is 4,00 kg.").append(System.lineSeparator());
        sb.append("Price for a Box is 13,30 euro.").append(System.lineSeparator());
        sb.append("For delivery service pay - Sender with Cash").append(System.lineSeparator());
        String currentText = sb.toString().trim();
        Assert.assertEquals(currentText,baseTrackingNumber.toString());







    }








}
