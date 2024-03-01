package petStore;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalThrow() {
        Animal animal = null;
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
    }

    @Test
    public void testGetCountToList() {
        Animal animal = new Animal("dog",10,2.2);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        Assert.assertEquals(petStore.getCount(),1);
    }
    @Test
    public void testFindAnimalsWithMaxKilograms() {
        Animal animal = new Animal("dog",10,28.2);
        Animal animal2 = new Animal("CAt",10,26.2);
        Animal animal3 = new Animal("Human",70,10.2);
        Animal animal4 = new Animal("Snake",60,25.2);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);
        petStore.addAnimal(animal4);
        List<Animal> allAnimalsWithMaxKilograms = petStore.findAllAnimalsWithMaxKilograms(50);
        Assert.assertEquals(allAnimalsWithMaxKilograms.size(),2);
    }

    @Test
    public void testFindMostExpensiveAnimal() {
        Animal animal = new Animal("dog",10,28.2);
        Animal animal2 = new Animal("CAt",10,26.2);
        Animal animal3 = new Animal("Human",70,70.2);
        Animal animal4 = new Animal("Snake",60,25.2);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);
        petStore.addAnimal(animal4);
        Animal theMostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(theMostExpensiveAnimal,animal3);
    }

    @Test
    public void testFindAllAnimalBySpecie () {
        Animal animal = new Animal("dog",10,28.2);
        Animal animal2 = new Animal("dog",10,26.2);
        Animal animal3 = new Animal("dog",70,10.2);
        Animal animal4 = new Animal("Snake",60,25.2);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);
        petStore.addAnimal(animal4);
        List<Animal> dog = petStore.findAllAnimalBySpecie("dog");
        Assert.assertEquals(dog.size(),3);
    }
    @Test
    public void testUnmodifiableList () {
        Animal animal = new Animal("dog",10,28.2);
        Animal animal2 = new Animal("dog",10,26.2);
        Animal animal3 = new Animal("dog",70,10.2);
        Animal animal4 = new Animal("Snake",60,25.2);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);
        petStore.addAnimal(animal4);
        List<Animal> animals = petStore.getAnimals();
        Assert.assertEquals(animals.size(),4);
    }


}

