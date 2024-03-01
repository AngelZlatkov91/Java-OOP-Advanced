import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

public class TestDataBaseExtended {
    private p02_ExtendedDatabase.Database database;
    private static final Person[] PEOPLE = {new Person(1,"Angel"),
                                             new Person(2,"Plamen"),
                                            new Person(3,"Vasil")};
    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    // test - constructor
    // test - constructor - дали създава правилен обект
    @Test
    public void testConstructorHasCreateCorrectObject() throws OperationNotSupportedException {
         Person[] databaseElements = database.getElements();
        Assert.assertArrayEquals("Arrays are not the same!",PEOPLE, databaseElements);
        Assert.assertEquals("Count of element is incorrect!",database.getElementsCount(), PEOPLE.length);
        Assert.assertEquals("Index is incorrect!",PEOPLE.length-1,database.getIndex());
    }

    // test - constructor - дали създава обект неправилно и отчита грешка с повече от 16
    @Test (expected = OperationNotSupportedException.class)
    public void testConstructorThrowExceptionIfHaveMoreThanSixteenElements() throws OperationNotSupportedException {
       Person[] people = new Person[17];
        new Database(people);
    }
    @Test (expected = OperationNotSupportedException.class)
    public void testConstructorThrowExceptionIfHaveNoElement() throws OperationNotSupportedException {
        Person[] numbers = new Person[0];
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
        Person[] elementsBefor = database.getElements();
        database.add(new Person(4,"Sevi"));
        Person[] elementsAfter = database.getElements();
        Assert.assertEquals("Invalid area",elementsBefor.length+1,elementsAfter.length);
        Person lastPerson = elementsAfter[elementsAfter.length-1];
        Assert.assertEquals(lastPerson.getId(),4);
        Assert.assertEquals(lastPerson.getUsername(),"Sevi");
    }

    //TestRemove
    // успешно премахнат елемент
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        Person[] elementsBefore = database.getElements();
        database.remove();
        Person[] elementsAfter = database.getElements();
        Assert.assertEquals("Invalid remove",elementsBefore.length-1,elementsAfter.length);
        Person lastPerson = elementsAfter[elementsAfter.length-1];
        Assert.assertEquals(lastPerson.getId(),2);
        Assert.assertEquals(lastPerson.getUsername(),"Plamen");
    }
    //не успешно премахване
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowExceptionOnInvalidIndex () throws OperationNotSupportedException {
        for (int i = 0; i < PEOPLE.length; i++) {
            database.remove();
        }
        database.remove();
    }


    //findByUserName
    //UserName = null
    @Test (expected = OperationNotSupportedException.class)
    public void testFindByUserNameThrowExceptionWithNullParamerar() throws OperationNotSupportedException {
        database.findByUsername(null);
    }
    //UserName = validName
    @Test
    public void testFindByUserNameValidName() throws OperationNotSupportedException {
        Person person = database.findByUsername("Plamen");
        Assert.assertEquals("Invalid Id of the taken Person!",person.getId(),2);
        Assert.assertEquals("Invalid UserName of the taken Person!",person.getUsername(),"Plamen");
    }
    //UserName = more than one same name
    @Test(expected = OperationNotSupportedException.class)
    public void testFynByUserNameMoreThanSamePerson() throws OperationNotSupportedException {
        database.add(new Person(4,"Plamen"));
        database.findByUsername("Plamen");
    }
    // UserName = No such name
    @Test (expected = OperationNotSupportedException.class)
    public void testFindByUSerNameNotExisting() throws OperationNotSupportedException {
        database.findByUsername("Ivaylo");
    }





    //findById

    //UserName = validName
    @Test
    public void testFindIDValidName() throws OperationNotSupportedException {
        Person person = database.findById(2);
        Assert.assertEquals("Invalid Id of the taken Person!",person.getId(),2);
        Assert.assertEquals("Invalid UserName of the taken Person!",person.getUsername(),"Plamen");
    }
    //UserName = more than one same name
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdMoreThanSamePerson() throws OperationNotSupportedException {
        database.add(new Person(2,"Plamen"));
        database.findById(2);
    }
    // UserName = No such name
    @Test (expected = OperationNotSupportedException.class)
    public void testFindIdeNotExisting() throws OperationNotSupportedException {
        database.findById(6);
    }




}
