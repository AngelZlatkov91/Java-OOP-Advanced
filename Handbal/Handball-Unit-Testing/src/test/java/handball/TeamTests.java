package handball;


import org.junit.Assert;
import org.junit.Test;

public class TeamTests {
    @Test (expected = NullPointerException.class)
    public void testCreateWithInvalidName(){
          Team team = new Team(null,5);
    }
    @Test (expected = NullPointerException.class)
    public void testCreateWithInvalidNameWithSpace(){
        Team team = new Team(" ",5);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCreateWithInvalidPosition(){
        Team team = new Team("Angel",-4);
    }
    @Test
    public void testCreateTeamCorrect(){
        Team team = new Team("Angel",10);
        String name = team.getName();
        Assert.assertEquals(name,team.getName());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddHandballPlayernInCorrect(){
        HandballPlayer handballPlayer = new HandballPlayer("Angel");
        HandballPlayer handballPlayer1 = new HandballPlayer("Ivan");
        Team team = new Team("Petrich",1);
            team.add(handballPlayer);
            team.add(handballPlayer1);

    }
    @Test
    public void testAddHandballPlayerCorrect(){
        HandballPlayer handballPlayer = new HandballPlayer("Angel");
        HandballPlayer handballPlayer1 = new HandballPlayer("Ivan");
        Team team = new Team("Petrich",2);
        team.add(handballPlayer);
        team.add(handballPlayer1);
        Assert.assertEquals(team.getPosition(),team.getCount());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testRemoveInCorrect(){
        Team team = new Team("Petrich",2);
        HandballPlayer handballPlayer = new HandballPlayer("Angel");
        HandballPlayer handballPlayer1 = new HandballPlayer("Ivan");
        team.add(handballPlayer);
        team.add(handballPlayer1);
        team.remove("Kiril");
    }
    @Test
    public void testRemoveCorrect(){
        Team team = new Team("Petrich",2);
        HandballPlayer handballPlayer = new HandballPlayer("Angel");
        HandballPlayer handballPlayer1 = new HandballPlayer("Ivan");
        team.add(handballPlayer);
        team.add(handballPlayer1);
        team.remove("Ivan");
        Assert.assertEquals(team.getCount(),1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerForAnotherTeamThrow() {
        Team team = new Team("Petrich",2);
        HandballPlayer handballPlayer = new HandballPlayer("Angel");
        HandballPlayer handballPlayer1 = new HandballPlayer("Ivan");
        team.add(handballPlayer);
        team.playerForAnotherTeam("Ivan");
    }
    @Test
    public void testPlayerForAnotherTeamCorrect() {
        Team team = new Team("Petrich",2);
        HandballPlayer handballPlayer = new HandballPlayer("Angel");
        HandballPlayer handballPlayer1 = new HandballPlayer("Ivan");
        team.add(handballPlayer);
        team.add(handballPlayer1);
        team.playerForAnotherTeam("Ivan");
        handballPlayer1.setActive(false);
    }
    @Test
    public void testGetStatistics(){
        Team team = new Team("Petrich",2);
        HandballPlayer handballPlayer = new HandballPlayer("Angel");
        team.add(handballPlayer);
        String text = "The player Angel is in the team Petrich.";
        String currenttext = team.getStatistics();
        Assert.assertEquals(text,currenttext);
    }

}
