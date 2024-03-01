package bank.entities.bank;

import bank.common.ExceptionMessages;
import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseBank implements Bank {
    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;

    public BaseBank(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Collection<Loan> getLoans() {
        return loans;
    }

    @Override
    public Collection<Client> getClients() {
        return clients;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumOfInterestRates() {
        int sum = 0;
        for (Loan loan : this.getLoans()) {
            sum += loan.getInterestRate();
        }
        return sum;
    }

    @Override
    public void addClient(Client client) {
        if (this.capacity > this.getClients().size()) {
            this.clients.add(client);
            return;
        }
        throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT);

    }

    @Override
    public void removeClient(Client client) {
        this.getClients().remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        this.getLoans().add(loan);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name).append(", Type: ").append(this.getClass().getSimpleName()).append(System.lineSeparator());
        sb.append("Clients: ");
        if (this.clients.isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
           sb.append(this.clients.stream().map(Client::getName).collect(Collectors.joining(", "))).append(System.lineSeparator());
        }
        sb.append("Loans: ").append(this.getLoans().size()).append(", Sum of interest rates: ").append(this.sumOfInterestRates());
        return sb.toString();
    }

}



