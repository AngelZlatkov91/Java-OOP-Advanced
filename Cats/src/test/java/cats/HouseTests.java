package cats;

import org.junit.Assert;
import org.junit.Test;

public class HouseTests {
    @Test (expected = NullPointerException.class)
    public void testCreateHouseWithInCorrectName(){
        House house = new House(null,1);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCreateHouseWithInCorrectCapacity(){
        House house = new House("MainHouse",-1);
    }
    @Test
    public void testCreateHouseCorrect(){
        House house = new House("MainHouse",5);
        Assert.assertEquals(house.getName(),"MainHouse");
    }
    @Test
    public void testCreateHouseCorrectCapacity(){
        House house = new House("MainHouse",5);
        Assert.assertEquals(house.getCapacity(),5);
    }
    @Test
    public void testGetCountCatInHouse(){
        House house = new House("MainHouse",5);
        Cat cat = new Cat("kitti");
        Cat cat1 = new Cat("kette");
        Cat cat2 = new Cat("katta");
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
        Assert.assertEquals(house.getCount(),3);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddCatInHouseNoCapacity(){
        House house = new House("MainHouse",2);
        Cat cat = new Cat("kitti");
        Cat cat1 = new Cat("kette");
        Cat cat2 = new Cat("katta");
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatByNameThrow(){
        House house = new House("MainHouse",5);
        Cat cat = new Cat("kitti");
        Cat cat1 = new Cat("kette");
        Cat cat2 = new Cat("katta");
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
        house.removeCat("kuttu");
    }
    @Test
    public void testRemoveCatByName(){
        House house = new House("MainHouse",5);
        Cat cat = new Cat("kitti");
        Cat cat1 = new Cat("kette");
        Cat cat2 = new Cat("katta");
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
        house.removeCat("kitti");
        Assert.assertEquals(house.getCount(),2);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCatForSaleThrow(){
        House house = new House("MainHouse",5);
        Cat cat = new Cat("kitti");
        Cat cat1 = new Cat("kette");
        Cat cat2 = new Cat("katta");
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
        house.catForSale("kuttu");
    }
    @Test
    public void testCatForSale(){
        House house = new House("MainHouse",5);
        Cat cat = new Cat("kitti");
        Cat cat1 = new Cat("kette");
        Cat cat2 = new Cat("katta");
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
        Cat kitti = house.catForSale("kitti");
        Assert.assertFalse(kitti.isHungry());
    }
    @Test
    public void testGetStatistic() {
        House house = new House("MainHouse",5);
        Cat cat = new Cat("kitti");
        house.addCat(cat);
        String currentText = house.statistics();
        String expected = "The cat kitti is in the house MainHouse!";
        Assert.assertEquals(currentText,expected);

    }



}
