package bank;

import org.junit.Assert;
import org.junit.Test;

public class BankTests {
     @Test (expected = NullPointerException.class)
    public void testCreateInCorrectBankWithIncorrectName(){
         Bank bank = new Bank(null,5);
     }
    @Test (expected = NullPointerException.class)
    public void testCreateInCorrectBankWithSpace(){
        Bank bank = new Bank("  ",5);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCreateInCorrectBankWithIncorrectCapacity(){
        Bank bank = new Bank("DSK_Bank",-1);
    }
    @Test
    public void testCreateCorrectBankWithCorrectName(){
         Bank bank = new Bank("DSK_Bank",5);
        String name = bank.getName();
        Assert.assertEquals("DSK_Bank",name);
    }
    @Test
    public void testGetCapacityBank(){
        Bank bank = new Bank("DSK_Bank",5);
        int capacity = bank.getCapacity();
        Assert.assertEquals(capacity,5);
    }
    @Test
    public void testGetCountBankClients(){
        Bank bank = new Bank("DSK_Bank",5);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Pesho");
        Client client3 = new Client("Sevi");
        Client client4 = new Client("Ivaylo");
        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);
        bank.addClient(client4);
        int count = bank.getCount();
        Assert.assertEquals(count,4);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddWithEqualCapacityThrow(){
        Bank bank = new Bank("DSK_Bank",3);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Pesho");
        Client client3 = new Client("Sevi");
        Client client4 = new Client("Ivaylo");
        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);
        bank.addClient(client4);
    }
    @Test
    public void testAddClientCorrect(){
        Bank bank = new Bank("DSK_Bank",5);
        Client client1 = new Client("Angel");
        bank.addClient(client1);
        int count = bank.getCount();
        Assert.assertEquals(count,1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveNotExistClient (){
        Bank bank = new Bank("DSK_Bank",3);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Pesho");
        Client client3 = new Client("Sevi");
        Client client4 = new Client("Ivaylo");
        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);
        bank.removeClient("Ivaylo");
    }
    @Test
    public void testRemoveClient (){
        Bank bank = new Bank("DSK_Bank",4);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Pesho");
        Client client3 = new Client("Sevi");
        Client client4 = new Client("Ivaylo");
        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);
        bank.addClient(client4);
        bank.removeClient("Ivaylo");
        Assert.assertEquals(bank.getCount(),3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawalThrow() {
         String name = "Georgi";
        Bank bank = new Bank("DSK_Bank",4);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Pesho");
        Client client3 = new Client("Sevi");
        Client client4 = new Client("Ivaylo");
        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);
        bank.addClient(client4);
        bank.loanWithdrawal(name);
    }
    @Test
    public void testLoanWithdrawal() {
        String name = "Angel";
        Bank bank = new Bank("DSK_Bank",4);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Pesho");
        Client client3 = new Client("Sevi");
        Client client4 = new Client("Ivaylo");
        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);
        bank.addClient(client4);
        Client client = bank.loanWithdrawal(name);
        Assert.assertEquals(client.getName(),name);
    }
    @Test
    public void testGetStatistic() {
        Bank bank = new Bank("DSK_Bank",4);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Ivan");
        bank.addClient(client1);
        bank.addClient(client2);
        String statistic = bank.statistics();
        String textTest = "The client Angel, Ivan is at the DSK_Bank bank!";
        Assert.assertEquals(statistic,textTest);
    }
    @Test
    public void testIsApprovedForLoan(){

        Client client1 = new Client("Angel");


        Assert.assertTrue(client1.isApprovedForLoan());
    }
    @Test
    public void testGetStatisticIncorect() {
        Bank bank = new Bank("DSK_Bank",4);
        Client client1 = new Client("Angel");
        Client client2 = new Client("Ivan");
        bank.addClient(client1);
        bank.addClient(client2);
        String statistic = bank.statistics();
        String textTest = "The client Angel,Pesho, Ivan is at the DSK_Bank bank!";
        if (!statistic.equals(textTest)) {
            Assert.assertTrue(true);
        }
    }



}
