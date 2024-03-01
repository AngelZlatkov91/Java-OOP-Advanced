import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;

public class DataBaseTest {
    private Database database;
    private static final Integer[] NUMBERS = {3,4, 5, 6, 8,};
    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    // test - constructor
    // test - constructor - дали създава правилен обект
    @Test
    public void testConstructorHasCreateCorrectObject() throws OperationNotSupportedException {

        Assert.assertArrayEquals("Arrays are not the same!",NUMBERS, database.getElements());
        Assert.assertEquals("Count of element is incorrect!",database.getElementsCount(), NUMBERS.length);
        Assert.assertEquals("Index is incorrect!",NUMBERS.length-1,database.getIndex());
    }

// test - constructor - дали създава обект неправилно и отчита грешка с повече от 16
    @Test (expected = OperationNotSupportedException.class)
    public void testConstructorThrowExceptionIfHaveMoreThanSixteenElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
       new Database(numbers);
    }
    @Test (expected = OperationNotSupportedException.class)
    public void testConstructorThrowExceptionIfHaveNoElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }


    //testAdd - добавяме null
    @Test(expected = OperationNotSupportedException.class)
    public void TestAddShouldThrowExceptionNullParam() throws OperationNotSupportedException {
        database.add(null);
    }
    //testAdd - успешно добавяне на елемент
    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        Integer[] elementsBefor = database.getElements();
        database.add(64);
        Integer[] elementsAfter = database.getElements();
        Assert.assertEquals("Invalid area",elementsBefor.length+1,elementsAfter.length);
        Assert.assertEquals(elementsAfter[elementsAfter.length-1],Integer.valueOf(64));
    }

    //TestRemove
    // успешно премахнат елемент
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        Integer[] elementsBefore = database.getElements();
        database.remove();
        Integer[] elementsAfter = database.getElements();
        Assert.assertEquals("Invalid remove",elementsBefore.length-1,elementsAfter.length);
        Assert.assertEquals(elementsAfter[elementsAfter.length-1],Integer.valueOf(6));
    }
    //не успешно премахване
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowExceptionOnInvalidIndex () throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }





}
