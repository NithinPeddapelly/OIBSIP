import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;
class Transaction {
    private String type;
    private double amount;
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
}
class Account {
    private String USER_ID;
    private String USER_PIN;
    private double BALANCE;
    private List<Transaction> T_History;
    public Account(String USER_ID, String USER_PIN, double BALANCE) {
        this.USER_ID = USER_ID;
        this.USER_PIN = USER_PIN;
        this.BALANCE = BALANCE;
        this.T_History = new ArrayList<>();
    }
    public boolean authenticate(String USER_ID, String USER_PIN) {
        return this.USER_ID.equals(USER_ID) && this.USER_PIN.equals(USER_PIN);
    }
    public double getBALANCE() {
        return BALANCE;
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= BALANCE) {
            if(amount%500==0)
            {
            BALANCE -= amount;
            T_History.add(new Transaction("Withdraw", amount));
            System.out.println("Amount withdrawn: RS" + amount);
        }
        else{
            System.out.println("Invalid withdrawl amount");
        }
     }
      else {
            System.out.println("Insufficient BALANCE.");
        }
    }
    public void deposit(double amount) {
        if (amount > 0) {
            BALANCE += amount;
            T_History.add(new Transaction("Deposit", amount));
            System.out.println("Amount deposited: RS" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    public void transfer(double amount, Account recipient) {
        if (amount > 0 ) {
            if(amount <= BALANCE){
            BALANCE -= amount;
            recipient.BALANCE += amount;
            T_History.add(new Transaction("Transfer to " + recipient.USER_ID, amount));
            System.out.println("Amount transferred: RS" + amount);
        }
        else {
            System.out.println("Insufficient BALANCE.");
        }
    }
        else {
            System.out.println("Invalid transfer amount ");
        }
    }

    public void printT_History() {
        System.out.println("Transaction History:");
        if (T_History.isEmpty()) {
            System.out.println("No transaction history found");
        } else {
            for (Transaction transaction : T_History) {
                System.out.println(transaction.getType() + ": RS" + transaction.getAmount());
            }
        }
    }
}
public class ATM{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Account account = new Account("898989", "8989", 10000.0);

            System.out.println("Welcome to ATM");

            System.out.print("Enter User ID: ");
            String USER_ID = sc.nextLine();

            System.out.print("Enter User PIN: ");
            String USER_PIN = sc.nextLine();

            if (account.authenticate(USER_ID, USER_PIN)) {
                int choice;
                do {
                    System.out.println("ATM Menu:");
                    System.out.println("1. Transaction History");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    System.out.println("5. BALANCE");
                    System.out.println("6. Quit");

                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            account.printT_History();
                            break;
                        case 2:
                            System.out.println("Only 500 notes are availabe");
                            System.out.print("Enter the amount to withdraw: RS ");
                            double withdrawAmount = sc.nextDouble();
                            sc.nextLine();
                            account.withdraw(withdrawAmount);
                            break;
                        case 3:
                            System.out.print("Enter the amount to deposit: RS ");
                            double depositAmount = sc.nextDouble();
                            sc.nextLine();
                            account.deposit(depositAmount);
                            break;
                        case 4:
                            System.out.print("Enter the recipient's User ID: ");
                            String recipientId = sc.nextLine();
                            System.out.print("Enter the amount to transfer: RS ");
                            double transferAmount = sc.nextDouble();
                            sc.nextLine();
                            Account recipient = new Account(recipientId, "", 0);
                            account.transfer(transferAmount, recipient);
                            break;
                        case 5:
                            System.out.println("CURRENT BALANCE: RS " + account.getBALANCE());
                            break;
                        case 6:
                            System.out.println("Thank you for using our ATM");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    System.out.println();
                } while (choice != 6);
            } else {
                System.out.println("Authentication failed. Invalid User ID or PIN.");
            }
        }
    }
}
