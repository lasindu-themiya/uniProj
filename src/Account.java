public class Account {
    protected int accNumber;
    protected int pin;
    protected double balance;

    public Account(int accNumber, int pin, double balance) {
        this.accNumber = accNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePIN(int enterPIN) {
        return pin == enterPIN;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount)
    {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient Funds");
        }
    }

    public void checkBalance() {
        System.out.println("Account Balance : " + balance);
    }
}
