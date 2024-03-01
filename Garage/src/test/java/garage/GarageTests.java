package garage;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GarageTests {
    //TODO: Test Garage class
    @Test (expected = IllegalArgumentException.class)
    public void testAddCarReturnThrow () {
        Garage garage = new Garage();
        Car car = null;
        garage.addCar(car);
    }
    @Test
    public void testAddCarSuccess() {
        Garage garage = new Garage();
        Car car = new Car("Ford",240,15000.00);
        garage.addCar(car);
        Assert.assertEquals(garage.getCount(),1);
    }
    @Test
    public void testGetMostExpensiveCar(){
        Garage garage = new Garage();
        Car car1 = new Car("Ford",240,15000.00);
        Car car2 = new Car("Audi",300,45000.00);
        Car car3 = new Car("Mercedes",400,150000.00);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(theMostExpensiveCar,car3);
    }
    @Test
    public void testFindAllCarsWithMaxSpeedAbove(){
        Garage garage = new Garage();
        Car car1 = new Car("Ford",240,15000.00);
        Car car2 = new Car("Audi",300,45000.00);
        Car car3 = new Car("Mercedes",400,150000.00);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(350);
        Car car = allCarsWithMaxSpeedAbove.get(0);
        Assert.assertEquals(car,car3);
    }
    @Test
    public void testAllCarsWithBrand(){
        Garage garage = new Garage();
        Car car1 = new Car("Ford",240,15000.00);
        Car car2 = new Car("Audi",300,45000.00);
        Car car3 = new Car("Mercedes",400,150000.00);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> audi = garage.findAllCarsByBrand("Audi");
        Car car = audi.get(0);
        Assert.assertEquals(car,car2);
    }
    @Test
    public void testGetUnmodifiableList() {
        Garage garage = new Garage();
        Car car1 = new Car("Ford",240,15000.00);
        Car car2 = new Car("Audi",300,45000.00);
        Car car3 = new Car("Mercedes",400,150000.00);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        garage.getCars()
    }
}