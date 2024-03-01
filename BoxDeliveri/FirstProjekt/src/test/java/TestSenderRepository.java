import org.BoxDeliver.Repository.SenderRepository;
import org.BoxDeliver.Sender.BaseSender;
import org.BoxDeliver.Sender.Sender;
import org.junit.Assert;
import org.junit.Test;

public class TestSenderRepository {
    @Test (expected = IllegalArgumentException.class)
    public void testAddExistingSender(){
        Sender sender = new BaseSender("Angel","Sofia","Prilep 69","0898383817");
        Sender sender2 = new BaseSender("IVan","Sofia","Prilep 69","0898383817");
        SenderRepository senderRepository = new SenderRepository();
        senderRepository.add(sender);
        senderRepository.add(sender);
    }
    @Test
    public void testAddSender() {
        Sender sender = new BaseSender("Angel","Sofia","Prilep 69","0898383817");
        SenderRepository senderRepository = new SenderRepository();
        senderRepository.add(sender);
        Assert.assertEquals(senderRepository.size(),1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstThrow(){
        Sender sender = new BaseSender("Angel","Sofia","Prilep 69","0898383817");
        SenderRepository senderRepository = new SenderRepository();
        senderRepository.add(sender);
        senderRepository.findFirst("Ivan");
    }
    @Test
    public void testFindFirstCorrect() {
        Sender sender = new BaseSender("Angel","Sofia","Prilep 69","0898383817");
        SenderRepository senderRepository = new SenderRepository();
        senderRepository.add(sender);
        Sender angel = senderRepository.findFirst("Angel");
        Assert.assertEquals(sender,angel);
    }
    @Test
    public void testIsHereFalse() {
        Sender sender = new BaseSender("Angel","Sofia","Prilep 69","0898383817");
        SenderRepository senderRepository = new SenderRepository();
        senderRepository.add(sender);
        Assert.assertFalse(senderRepository.isHere("Ivan"));
    }
    @Test
    public void testIsHereTrue() {
        Sender sender = new BaseSender("Angel","Sofia","Prilep 69","0898383817");
        SenderRepository senderRepository = new SenderRepository();
        senderRepository.add(sender);
        Assert.assertTrue(senderRepository.isHere("Angel"));
    }

}
