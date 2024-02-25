public class SavingAccount extends Account {
    public static double annualInterest = 1.08;
    private Account account;

    public SavingAccount(int accNumber, int pin, double balance) {
        super(accNumber, pin, balance);
        this.account = new Account(accNumber, pin, balance*annualInterest);
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
