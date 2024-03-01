import org.BoxDeliver.Deliver.BaseDeliver;

import org.junit.Assert;
import org.junit.Test;

public class TestBaseDeliver {
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDeliverWithNameNullThrow(){
        BaseDeliver deliver = new BaseDeliver(null,"asda", "sadsa", "adsad","ddadas");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDeliverWithTownNullThrow(){
        BaseDeliver deliver = new BaseDeliver("Angel"," ", " ","sadsa", "adsad");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDeliverWithPhoneNullThrow(){
        BaseDeliver deliver = new BaseDeliver("Angel","asda","","sadsa", " ");
    }
    @Test
    public void testCreateDeliverCorrect() {
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","165","Car Boris","0898383817");
        String getSenderName = deliver.getName();
        Assert.assertEquals(getSenderName,"Angel");
    }
    @Test
    public void testCreateDeliverCorrectOhneAddress() {
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","165",null,"0898383817");
        String getSenderAddress = deliver.getAddress();
        Assert.assertEquals(getSenderAddress,"");
    }
    @Test
    public void testSetInDeliver(){
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","165","Car Boris","0898383817");
        deliver.setName("Ango");
        deliver.setPhone("0123456798");
        deliver.setAddress("Sando Kitanov 9");
        String name = deliver.getName();
        Assert.assertEquals(name,"Ango");
        Assert.assertEquals(deliver.getPhone(),"0123456798");
    }
    @Test
    public void testToStringDeliver() {
        BaseDeliver deliver = new BaseDeliver("Angel","Petrich","165","Car Boris","0898383817");
        StringBuilder sb = new StringBuilder();
        sb.append("Deliver:").append(System.lineSeparator());
        sb.append("Name: Angel").append(System.lineSeparator());
        sb.append("Address: Car Boris").append(System.lineSeparator());
        sb.append("Town: Petrich: Post Code - 165").append(System.lineSeparator());
        sb.append("Phone: 0898383817").append(System.lineSeparator());
        String expected = sb.toString().trim();
        String current = deliver.toString();
        Assert.assertEquals(expected,current);

    }
}
