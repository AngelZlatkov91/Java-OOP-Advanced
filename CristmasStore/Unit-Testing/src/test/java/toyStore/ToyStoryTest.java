package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ToyStoryTest {
    private ToyStore toyStore;

    @Before
    public void init() {
        toyStore = new ToyStore();
    }

    @Test
    public void testInitToyStore(){
        Map<String, Toy> toyShelf = new LinkedHashMap<>();
        toyShelf.put("A", null);
       toyShelf.put("B", null);
       toyShelf.put("C", null);
        toyShelf.put("D", null);
        toyShelf.put("E", null);
        toyShelf.put("F", null);
       toyShelf.put("G", null);
        Assert.assertEquals(toyShelf,toyStore.getToyShelf());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddToyThrowWNonExist () throws OperationNotSupportedException {
       Toy toy = new Toy("Comsed", "1");
       toyStore.addToy("H",toy);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddToyThrowToTakenShel() throws OperationNotSupportedException {
        Toy toy = new Toy("Comsed", "1");
        Toy toy2 = new Toy("Hipol", "2");
        toyStore.addToy("A",toy);
        toyStore.addToy("A",toy2);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddToyThrowDuplicate() throws OperationNotSupportedException {
        Toy toy = new Toy("Comsed", "1");
        toyStore.addToy("A",toy);
        toyStore.addToy("B",toy);
    }
    @Test
    public void testAddToyShouldAddSuccess() throws OperationNotSupportedException {
        Toy toy = new Toy("Comsed", "1");
        toyStore.addToy("A",toy);
        Toy addToy = toyStore.getToyShelf().get("A");
        Assert.assertEquals(toy,addToy);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveToyThrowDuplicate() throws OperationNotSupportedException {
        Toy toy = new Toy("Comsed", "1");
        toyStore.addToy("A",toy);
        toyStore.removeToy("H",toy);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveNotExistToyThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Comsed", "1");
        Toy toy1 = new Toy("Fisher", "2");
        toyStore.addToy("A",toy);
        toyStore.removeToy("A",toy1);
    }
    @Test
    public void testRemoveSuccess() throws OperationNotSupportedException {
        Toy toy = new Toy("Comsed", "1");

        toyStore.addToy("A",toy);

        String result = toyStore.removeToy("A",toy);
        Assert.assertNull(toyStore.getToyShelf().get("A"));
        Assert.assertEquals("Remove toy:1 successfully!",result);

    }










}