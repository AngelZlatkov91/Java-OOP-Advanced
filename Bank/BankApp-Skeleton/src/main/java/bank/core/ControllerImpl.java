package bank.core;
import bank.common.ConstantMessages;
import bank.common.ExceptionMessages;
import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private LoanRepository loans;
    private Map<String,Bank> banks;

    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks = new LinkedHashMap<>();
    }

    @Override
    public String addBank(String type, String name) {
        Bank bank;
        if (type.equals("CentralBank")) {
            bank = new CentralBank(name);
        } else if (type.equals("BranchBank")) {
            bank = new BranchBank(name);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_BANK_TYPE);
        }
        this.banks.put(name,bank);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE,type);
    }

    @Override
    public String addLoan(String type) {
        Loan loan;
        if (type.equals("StudentLoan")) {
            loan = new StudentLoan();
        } else if (type.equals("MortgageLoan")) {
            loan = new MortgageLoan();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_LOAN_TYPE);
        }
        this.loans.addLoan(loan);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE,type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan loanForService = this.loans.findFirst(loanType);
        if (loanForService == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND,loanType));
        }
        Bank bank = this.banks.get(bankName);
        bank.addLoan(loanForService);
        this.loans.removeLoan(loanForService);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK,loanType,bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Client client = null;
        if (clientType.equals("Student")) {
            client = new Student(clientName,clientID,income);
        } else if (clientType.equals("Adult")) {
            client = new Adult(clientName,clientID,income);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }
        Bank bank = this.banks.get(bankName);
        String output = "";
        if (!isSuitableBank(clientType,bank)){
            output = ConstantMessages.UNSUITABLE_BANK;
        } else {
            bank.addClient(client);
            output = String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK,clientType,bankName);
        }
        return output;
    }

    private boolean isSuitableBank(String clientType, Bank bank) {
        String bankType = bank.getClass().getSimpleName();
        if (clientType.equals("Student") && bankType.equals("BranchBank")) {
            return true;
        } else if (clientType.equals("Adult") && bankType.equals("CentralBank")) {
            return true;
        }
        return false;
    }

    @Override
    public String finalCalculation(String bankName) {
        Bank bank = this.banks.get(bankName);
        double priceClient = bank.getClients().stream()
                .mapToDouble(Client::getIncome).sum();
        double loanPrice = bank.getLoans().stream()
                .mapToDouble(Loan::getAmount).sum();

        return String.format(ConstantMessages.FUNDS_BANK,bankName,priceClient + loanPrice);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.banks.values().stream().map(Bank::getStatistics).collect(Collectors.joining(System.lineSeparator())));

        return sb.toString().trim();
    }
}
