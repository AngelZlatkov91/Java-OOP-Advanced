package scubaDive;


import org.junit.Assert;
import org.junit.Test;

public class DivingTests {
    @Test (expected = NullPointerException.class)
    public void testCreateDivingWithNullName() {
        Diving diving = new Diving(null,10);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCreateDivingWithZeroCapacity() {
        Diving diving = new Diving("Black Sea",-1);
    }
    @Test
    public void testCreateDivingCorrect (){
        Diving diving = new Diving("Black Sea", 10);
        Assert.assertEquals(diving.getName(),"Black Sea");
        Assert.assertEquals(10,diving.getCapacity());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddDiverWithNotHaveCapacity() {
        Diving diving = new Diving("Black Sea", 1);
        DeepWaterDiver deepWaterDiver1 = new DeepWaterDiver("Angel",60);
        DeepWaterDiver deepWaterDiver2 = new DeepWaterDiver("Ivan",60);
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddDiverIsExist() {
        Diving diving = new Diving("Black Sea", 2);
        DeepWaterDiver deepWaterDiver1 = new DeepWaterDiver("Angel",60);
        DeepWaterDiver deepWaterDiver2 = new DeepWaterDiver("Angel",60);
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
    }
    @Test
    public void testAddDiverCorrect() {
        Diving diving = new Diving("Black Sea", 2);
        DeepWaterDiver deepWaterDiver1 = new DeepWaterDiver("Angel",60);
        DeepWaterDiver deepWaterDiver2 = new DeepWaterDiver("Ivan",60);
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
        Assert.assertEquals(2,diving.getCount());
    }

    @Test
    public void testRemoveDiverFromDiving() {
        Diving diving = new Diving("Black Sea", 2);
        DeepWaterDiver deepWaterDiver1 = new DeepWaterDiver("Angel",60);
        DeepWaterDiver deepWaterDiver2 = new DeepWaterDiver("Ivan",60);
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
        boolean isRemoved = diving.removeDeepWaterDiver("Angel");
        Assert.assertTrue(isRemoved);
    }
    @Test
    public void testRemoveDiverFromDivingNotExist() {
        Diving diving = new Diving("Black Sea", 2);
        DeepWaterDiver deepWaterDiver1 = new DeepWaterDiver("Angel",60);
        DeepWaterDiver deepWaterDiver2 = new DeepWaterDiver("Ivan",60);
        diving.addDeepWaterDiver(deepWaterDiver1);
        diving.addDeepWaterDiver(deepWaterDiver2);
        boolean isRemoved = diving.removeDeepWaterDiver("Petar");
        Assert.assertFalse(isRemoved);
    }
    @Test
    public void testDiver() {
        DeepWaterDiver deepWaterDiver = new DeepWaterDiver("Angel",60.00);
        double currentOxygen = deepWaterDiver.getOxygen();
        boolean isEquals = currentOxygen == 60.00;
        Assert.assertTrue(isEquals);
    }






}
