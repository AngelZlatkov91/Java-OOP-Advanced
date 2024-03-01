package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CarShopTests {
     private CarShop carShop;
     private Car car ;

     @Before
    public void seUp(){
         carShop = new CarShop();
         car = new Car("BMW", 231, 15000);
         carShop.add(car);
     }


     @Test (expected = UnsupportedOperationException.class)
    public void testReturnUnmodifiableList () {
        carShop.getCars().set(0,car);
     }
     @Test
    public void testGetCarsReturnList(){
         List<Car> cars = carShop.getCars();
         Assert.assertEquals(1,cars.size());
     }

     @Test
    public void testGetCountCorrect() {
         Assert.assertEquals(1,carShop.getCount());
     }
     @Test
    public void testWhenGivenShopFindCarsWithMaxHorsePowerReturnCorrect() {
         Car car1 = new Car("Bugati",500,100000);
         Car car2 = new Car("FERari",450,100000);
         Car car3 = new Car("Lamborgini",499,100000);
         carShop.add(car1);
         carShop.add(car2);
         carShop.add(car3);
         List<Car> expected = List.of(car1,car3);
         List<Car> allCarsWithMaxHorsePower = carShop.findAllCarsWithMaxHorsePower(460);
             Assert.assertEquals(expected,allCarsWithMaxHorsePower);
     }

    @Test (expected = NullPointerException.class)
    public void testAddNullCarThrow(){
          Car car1 = null;
          carShop.add(car1);
    }
    @Test
    public void testAddCarSuccess(){
        Car car1 = new Car("Bugati",500,100000);
        carShop.add(car1);
        Assert.assertEquals(2,carShop.getCount());
    }
    @Test
    public void testRemoveCar(){
        Car car1 = new Car("Bugati",500,100000);
        carShop.add(car1);
        Assert.assertEquals(2,carShop.getCount());
        carShop.remove(car1);
        Assert.assertEquals(1,carShop.getCount());
    }

    @Test
    public void testCarsWithMostExpensive(){
        Car car1 = new Car("Bugati",500,100000);
        Car car2 = new Car("FERari",450,10000);
        Car car3 = new Car("Lamborgini",499,1000);
        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);
        Car theMostLuxuryCar = carShop.getTheMostLuxuryCar();
        Assert.assertEquals(theMostLuxuryCar,car1);
    }
    @Test
    public void testFindCarsByModel(){
        Car car1 = new Car("Bugati",500,100000);
        Car car2 = new Car("FERari",450,10000);
        Car car3 = new Car("Bugati",499,1000);
        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);
        List<Car> car11 = List.of(car1, car3);
        List<Car> bugati = carShop.findAllCarByModel("Bugati");
        Assert.assertEquals(car11,bugati);
    }





}

