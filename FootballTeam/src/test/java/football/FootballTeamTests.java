package football;

import org.junit.Assert;
import org.junit.Test;

public class FootballTeamTests {
@Test (expected = NullPointerException.class)
    public void testCreateTeamThrowFalseName() {
    FootballTeam footballTeam = new FootballTeam(null,20);
}
    @Test (expected = IllegalArgumentException.class)
    public void testCreateTeamThrowFalseVacantPosition() {
        FootballTeam footballTeam = new FootballTeam("Levski",-1);
    }
    @Test
    public void testCreateTeamCorrect() {
        FootballTeam footballTeam = new FootballTeam("Levski",20);
        Assert.assertEquals(footballTeam.getName(),"Levski");
        Assert.assertEquals(footballTeam.getVacantPositions(),20);
    }
    @Test
    public void testCountTeam () {
        FootballTeam footballTeam = new FootballTeam("Levski",20);
         Footballer footballer = new Footballer("Ivan");
         footballTeam.addFootballer(footballer);
         int count = footballTeam.getCount();
        Assert.assertEquals(count,1);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddPlayerThrow() {
        FootballTeam footballTeam = new FootballTeam("Levski",2);
        Footballer footballer = new Footballer("Ivan");
        Footballer footballer1 = new Footballer("Gosho");
        Footballer footballer2 = new Footballer("Pesho");
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSaleNotExistingPLayerThrow() {
        FootballTeam footballTeam = new FootballTeam("Levski",10);
        Footballer footballer = new Footballer("Ivan");
        Footballer footballer1 = new Footballer("Gosho");
        Footballer footballer2 = new Footballer("Pesho");
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        Footballer footballer3 = footballTeam.footballerForSale("Kiril");
    }
    @Test (expected = IllegalArgumentException.class)
    public void testRemovePlayerThrow(){
        FootballTeam footballTeam = new FootballTeam("Levski",10);
        Footballer footballer = new Footballer("Ivan");
        Footballer footballer1 = new Footballer("Gosho");
        Footballer footballer2 = new Footballer("Pesho");
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.removeFootballer("Pesho");
    }
    @Test
    public void testRemovePlayer(){
        FootballTeam footballTeam = new FootballTeam("Levski",10);
        Footballer footballer = new Footballer("Ivan");
        Footballer footballer1 = new Footballer("Gosho");
        Footballer footballer2 = new Footballer("Pesho");
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.removeFootballer("Gosho");
        int count = footballTeam.getCount();
        org.junit.Assert.assertEquals(count,2);
    }
    @Test
    public void testFootballerForSale() {
            FootballTeam footballTeam = new FootballTeam("Levski",2);
            Footballer footballer = new Footballer("Ivan");
            Footballer footballer1 = new Footballer("Gosho");

            footballTeam.addFootballer(footballer);
            footballTeam.addFootballer(footballer1);
            footballTeam.footballerForSale("Gosho");
        boolean active = footballer1.isActive();
        Assert.assertFalse(active);
    }
    @Test
    public void testGetStatistic () {
        FootballTeam footballTeam = new FootballTeam("Levski",2);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.addFootballer(footballer);
        String currentText = "The footballer Ivan is in the team Levski.";
        String statistics = footballTeam.getStatistics();
        Assert.assertEquals(currentText,statistics);
    }






}
