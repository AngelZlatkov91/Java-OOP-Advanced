import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstockTest {
    private ProductStock products;
    @Before
    public void setUp() {
        this.products = new Instock();
    }

     @Test
    public void testAddProduct (){

        Product product = new Product("Test",10,10);
        products.add(product);
         Assert.assertTrue(products.contains(product));
    }
    @Test
    public void testContains(){

        Product product = new Product("Test",10,10);
        Assert.assertFalse(products.contains(product));
    }

    @Test
    public void testContainsReturnTrue(){

        Product product = null;
        double price = 8.0;
        int quantity = 1;
        for (int i = 0; i < 50; i++) {
            Product produc = new Product("Test - " + i,price++,quantity++);
            products.add(produc);
            if (i %2 ==0) {
                product = produc;
            }
        }
       Assert.assertTrue(products.contains(product));
    }
    @Test
    public void testCountReturnZeroOrEmpty(){

         Assert.assertEquals(0,products.getCount());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowOnInvalidIndex (){

        products.find(100);
    }
    @Test
    public void testFindReturnCorrectProduct(){
        Product product1 = new Product("Test - " +1,10,10);
        Product product2 = new Product("Test - " +2,10,10);
        Product product3 = new Product("Test - " +3,10,10);
        Product product4 = new Product("Test - " +4,10,10);
        Product product5 = new Product("Test - " +5,10,10);
        this.products.add(product1);
        this.products.add(product2);
        this.products.add(product3);
        this.products.add(product4);
        this.products.add(product5);
        Assert.assertEquals(product3,this.products.find(2));

    }

    @Test
    public void testChangeQuantity(){
        Product product1 = new Product("Test - " +1,10,10);
        this.products.add(product1);
        this.products.changeQuantity("Test - 1",20);
        Assert.assertEquals(20,product1.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityThrowException() {
        this.products.changeQuantity("Test - 1",20);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFindByLabelShouldThrowException() {
        this.products.findByLabel("Angel");
    }

    @Test
    public void testFindByLabelCorrect(){
        Product product = new Product("Angel",10, 25);
        this.products.add(product);
        Product expected = this.products.findByLabel("Angel");
        Assert.assertEquals(expected,product);
    }

    @Test
    public void testFindFirstByAlphabeticalOrderWith3Product() {
        Product first = new Product("A",10 , 10);
        Product second = new Product("B",10 , 10);
        Product third = new Product("C",10 , 10);
        this.products.add(first);
        this.products.add(second);
        this.products.add(third);
        StringBuilder sb = new StringBuilder();
        this.products.findFirstByAlphabeticalOrder(3)
                .forEach(p->sb.append(p.label));
        Assert.assertEquals(sb.toString(),"ABC");
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmpty() {
       Iterable<Product>first = this.products.findFirstByAlphabeticalOrder(3);
        boolean hasNext = first.iterator().hasNext();
        Assert.assertFalse(hasNext);
    }

    @Test
    public void testFindFirstByAlphabeticalOrderWithInvalidData() {
        Product first = new Product("A",10 , 10);
        Product second = new Product("B",10 , 10);
        Product third = new Product("C",10 , 10);
        this.products.add(first);
        this.products.add(second);
        this.products.add(third);


        Iterable<Product> firstt = this.products.findFirstByAlphabeticalOrder(10);
        boolean hasNext = firstt.iterator().hasNext();
        Assert.assertFalse(hasNext);
    }

    @Test
    public void testFindAllInPRiceRangeShouldReturnEmptyCollection(){
        Iterable<Product> allInRange = this.products.findAllInRange(0, 1);
        boolean hasNext = allInRange.iterator().hasNext();
        Assert.assertFalse(hasNext);
    }


    @Test
    public void testFindAllInValidRange() {
        for (int i = 0; i < 10; i++) {
            Product first = new Product("A" + i,i+1 , 10);
            this.products.add(first);
        }
        Iterable<Product> allInRange = this.products.findAllInRange(1, 10);
        Iterator<Product> iterator = allInRange.iterator();
        for (int i = 2; iterator.hasNext(); i++) {
            Product next = iterator.next();
            Assert.assertEquals(i,next.price,0.00001);
        }
    }


    @Test
    public void testFindAllByPriceEmptyCollection() {
        Iterable<Product> allByPrice = this.products.findAllByPrice(10);
        boolean hasNext = allByPrice.iterator().hasNext();
        Assert.assertFalse(hasNext);

    }

    @Test
    public void testFindAllByPriceWithThreeProducts() {
        for (int i = 0; i < 10; i++) {
            Product first = new Product("A" + i,5 , 10);
            this.products.add(first);
        }
        Iterable<Product> allByPrice = this.products.findAllByPrice(5);
        allByPrice.forEach(p-> {
            Assert.assertEquals(5,p.getPrice(),0.0001);
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductShouldThrow () {
        this.products.findFirstMostExpensiveProducts(2);
    }

    @Test
    public void testFindMostExpensiveProduct(){
        Product first = new Product("A",10 , 10);
        Product second = new Product("B",50 , 10);
        Product third = new Product("C",40 , 10);
        this.products.add(first);
        this.products.add(second);
        this.products.add(third);

        Iterable<Product> firstMostExpensiveProducts = this.products.findFirstMostExpensiveProducts(2);

        double maxPrice = second.getPrice();
        for (Product p : firstMostExpensiveProducts) {
            Assert.assertEquals(maxPrice, p.getPrice(),0.00001);
            maxPrice = third.getPrice();
        }
    }

    @Test
    public void testFindAllByQuantityReturnEmptyCollection(){
        Iterable<Product> allByQuantity = this.products.findAllByQuantity(0);
        boolean hasNext = allByQuantity.iterator().hasNext();
        Assert.assertFalse(hasNext);
    }



    @Test
    public void testFindAllByQuantityReturnCorrect(){
        Product first = new Product("A",10 , 10);
        Product second = new Product("B",50 , 10);
        Product third = new Product("C",40 , 20);
        this.products.add(first);
        this.products.add(second);
        this.products.add(third);

        Iterable<Product> allByQuantity = this.products.findAllByQuantity(0);
        int quantity = first.getQuantity();
        for (Product p : allByQuantity) {
            Assert.assertEquals(quantity, p.getQuantity());

        }
        boolean hasNext = allByQuantity.iterator().hasNext();

        Assert.assertFalse(hasNext);
    }

    @Test
    public void testGetIterableShouldReturnAllResult() {

        List<Product> expected = new ArrayList<>();
        Product first = new Product("A",10 , 10);
        Product second = new Product("B",50 , 10);
        Product third = new Product("C",40 , 20);
        expected.add(first);
        expected.add(second);
        expected.add(third);
        this.products.add(first);
        this.products.add(second);
        this.products.add(third);

        Iterator<Product> iterator = this.products.iterator();

        for (Product p : expected) {
            Assert.assertTrue(iterator.hasNext());
            Product next = iterator.next();
            Assert.assertEquals(p,next);

        }


    }









}
