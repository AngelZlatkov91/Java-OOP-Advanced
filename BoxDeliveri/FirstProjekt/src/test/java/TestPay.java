import org.BoxDeliver.PayBoxDelivered.BasePay;
import org.junit.Assert;
import org.junit.Test;

public class TestPay {
    @Test(expected = IllegalArgumentException.class)
    public void testCreateBasePayIncorrectTypeClientThrow(){
        BasePay pay = new BasePay("asdasd","Cash");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateBasePayIncorrectTypePayThrow(){
        BasePay pay = new BasePay("Sender","sadsd");
    }
    @Test
    public void testCorrectCreatePay(){
        BasePay pay = new BasePay("Sender","Cash");
        String typeClient = pay.getTypeClient();
        String typePay = pay.getTypePay();
        Assert.assertEquals(typePay,"Cash");
        Assert.assertEquals(typeClient,"Sender");
    }
    @Test
    public void testCreatePayOhneService(){
        BasePay pay = new BasePay("Sender","Cash");
        Assert.assertFalse(pay.isService());
    }
    @Test
    public void testCreatePayWithService(){
        BasePay pay = new BasePay("Sender","Cash");
        pay.setService(true);
        Assert.assertTrue(pay.isService());
    }
    @Test
    public void testPriceForBox (){
        BasePay pay = new BasePay("Sender","Cash");
        double price = pay.price("132465798", 4);
        String textPrice = String.format("%.2f",price);
        Assert.assertEquals(textPrice,"11,30");
    }
    @Test
    public void testPriceForBoxWithService (){
        BasePay pay = new BasePay("Sender","Cash");
        pay.setService(true);
        double price = pay.price("132465798", 4);
        String textPrice = String.format("%.2f",price);
        Assert.assertEquals(textPrice,"13,30");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPriceThrow(){
        BasePay pay = new BasePay("Sender","Cash");
        pay.setService(true);
        double price = pay.price(null, 4);
    }


}
