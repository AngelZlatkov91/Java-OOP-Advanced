package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Test;

public class ExcavationTests {
    @Test (expected = NullPointerException.class)
    public void testCreateExcavationThrowName () {
        Excavation excavation = new Excavation(null,1);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCreateExcavationThrowCapacity () {
        Excavation excavation = new Excavation("Rupite",-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddArchaeologistThrow() {
        Excavation excavation = new Excavation("Rupite",2);
        Archaeologist archaeologist = new Archaeologist("Peter",100);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddSmallCapacityArchaeologistThrow() {
        Excavation excavation = new Excavation("Rupite",1);
        Archaeologist archaeologist = new Archaeologist("Peter",100);
        Archaeologist archaeologist2 = new Archaeologist("Ivan",200);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist2);
    }
    @Test
    public void testAddArchaeologistCorrect () {
        Excavation excavation = new Excavation("Rupite",2);
        Archaeologist archaeologist = new Archaeologist("Peter",100);
        Archaeologist archaeologist2 = new Archaeologist("Ivan",200);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist2);
        int count = excavation.getCount();
        Assert.assertEquals(count,2);
    }
    @Test
    public void testRemoveArchaeologist() {
        Excavation excavation = new Excavation("Rupite",2);
        Archaeologist archaeologist = new Archaeologist("Peter",100);
        Archaeologist archaeologist2 = new Archaeologist("Ivan",200);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist2);
        boolean ivan = excavation.removeArchaeologist("Ivan");
        Assert.assertTrue(ivan);
    }
    @Test
    public void testGetNameExcavation() {
        Excavation excavation = new Excavation("Rupite",2);
        String name = excavation.getName();
        Assert.assertEquals(name,"Rupite");
    }
    @Test
    public void testGetEnergyArchaeologist() {
        Archaeologist archaeologist = new Archaeologist("Peter",100.00);
        double energy = archaeologist.getEnergy();
        boolean isTrue = energy == 100.00;
        Assert.assertTrue(isTrue);

    }

}
