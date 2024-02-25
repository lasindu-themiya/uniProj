public class CurrentAccount extends Account {
    private Account account;

    public CurrentAccount(int accNumber, int pin, double balance) {
        super(accNumber, pin, balance);
        this.account = new Account(accNumber, pin, balance);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void checkBalance() {
        account.checkBalance();
    }
}

