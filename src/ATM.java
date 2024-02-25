import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ATM
{
    private Account currentAccount = null; // Store the currently logged-in account

    private Map<Integer, Account> accounts;

    public ATM() {
        accounts = new HashMap<>();
        // Create saving accounts
        accounts.put(123456, new SavingAccount(123456, 1234, 1000.0));
        accounts.put(124567, new SavingAccount( 124567, 1212, 2500.0));
        accounts.put(168053, new SavingAccount(168053, 2803, 2000.0));
        // Create current accounts
        accounts.put(789012, new CurrentAccount(789012, 5678, 10000.0));
        accounts.put(342254, new CurrentAccount(342254, 4533, 24500.0));
        accounts.put(563022, new CurrentAccount(563022, 8420, 32500.0));
    }

    public void loginCheck() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM");
        System.out.println("Do you want to use a card? (yes/no): ");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("yes")) {
            // With card transactions
            cardTransactions();
        } else if (choice.equals("no")) {
            // Without card transactions
            withoutCardTransactions();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
    }

    private void cardTransactions( ) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number : ");
        int accNumber = scanner.nextInt();

        System.out.println("Enter pin : ");
        int pin = scanner.nextInt();

        if (authenticate(accNumber, pin)) {
            System.out.println("Login successful.");
            currentAccount = accounts.get(accNumber);
            displayAccountDetails();
            handleTransactions();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void withoutCardTransactions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number : ");
        int accNumber = scanner.nextInt();

        System.out.println("Enter transfer amount: ");
        double transferAmount = scanner.nextDouble();

        if (transferAmount > 0) {
            Account senderAccount = accounts.get(accNumber);
            if (senderAccount != null) {
                // Transfer to a random account, just a placeholder logic
                int randomAccNumber = accounts.keySet().iterator().next();
                Account receiverAccount = accounts.get(randomAccNumber);
                if (receiverAccount != null) {
                    if (senderAccount instanceof CurrentAccount) {
                        ((CurrentAccount) senderAccount).withdraw(transferAmount);
                    } else if (senderAccount instanceof SavingAccount) {
                        ((SavingAccount) senderAccount).withdraw(transferAmount);
                    }
                    receiverAccount.deposit(transferAmount);
                    System.out.println("Transfer successful.");
                } else {
                    System.out.println("Receiver account not found.");
                }
            } else {
                System.out.println("Account not found.");
            }
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    private boolean authenticate(int accNumber, int pin) {
        Account account = accounts.get(accNumber);
        if (account != null) {
            return account.validatePIN(pin);
        }
        return false;
    }

    private void displayAccountDetails() {
        if (currentAccount != null) {
            currentAccount.checkBalance();
        }
    }

    private void handleTransactions() {
        boolean exit = false;
        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select transaction type:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    displayAccountDetails();
                    break;
                case 2:
                    System.out.println("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (currentAccount instanceof CurrentAccount) {
                        ((CurrentAccount) currentAccount).withdraw(withdrawAmount);
                    } else if (currentAccount instanceof SavingAccount) {
                        ((SavingAccount) currentAccount).withdraw(withdrawAmount);
                    }
                    displayAccountDetails();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Thank You for using our Bank..."+"\n"+"Exiting................");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.loginCheck();
    }
}
