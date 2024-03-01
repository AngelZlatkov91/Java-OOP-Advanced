import org.BoxDeliver.Sender.BaseSender;
import org.junit.Assert;
import org.junit.Test;

public class TestBaseSender {
    @Test(expected = IllegalArgumentException.class)
    public void testCreateSenderWithNameNullThrow(){
        BaseSender sender = new BaseSender(null,"asda", "sadsa", "adsad");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateSenderWithTownNullThrow(){
        BaseSender sender = new BaseSender("Angel"," ", "sadsa", "adsad");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateSenderWithPhoneNullThrow(){
        BaseSender sender = new BaseSender("Angel","asda", "sadsa", " ");
    }
    @Test
    public void testCreateSenderCorrect() {
        BaseSender sender = new BaseSender("Angel","Petrich","Car Boris","0898383817");
        String getSenderName = sender.getName();
        Assert.assertEquals(getSenderName,"Angel");
    }
    @Test
    public void testCreateSenderCorrectOhneAddress() {
        BaseSender sender = new BaseSender("Angel","Petrich",null,"0898383817");
        String getSenderAddress = sender.getAddress();
        Assert.assertEquals(getSenderAddress,"");
    }
    @Test
    public void testSetInSender(){
        BaseSender sender = new BaseSender("Angel","Petrich","Car Boris","0898383817");
             sender.setName("Ango");
             sender.setPhone("0123456798");
             sender.setAddress("Sando Kitanov 9");
        String name = sender.getName();
        Assert.assertEquals(name,"Ango");
        Assert.assertEquals(sender.getPhone(),"0123456798");
    }
    @Test
    public void testToStringSender() {
        BaseSender sender = new BaseSender("Angel","Petrich","Car Boris","0898383817");
        StringBuilder sb = new StringBuilder();
        sb.append("Sender:").append(System.lineSeparator());
        sb.append("Name: Angel").append(System.lineSeparator());
        sb.append("Address: Car Boris").append(System.lineSeparator());
        sb.append("Town: Petrich").append(System.lineSeparator());
        sb.append("Phone: 0898383817").append(System.lineSeparator());
        String expected = sb.toString().trim();
        String current = sender.toString();
        Assert.assertEquals(expected,current);

    }
}
