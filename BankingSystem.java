import java.util.Scanner;

public class BankingSystem {

    static class BankAccount {
        private String accountNumber;
        private String holderName;
        private double balance;

        public BankAccount(String accountNumber, String holderName, double balance) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = balance;
        }

        public void deposit(double amount) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }

        public void withdraw(double amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }

        public void printDetails() {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Holder Name: " + holderName);
            System.out.println("Balance: " + balance);
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    static class SavingsAccount extends BankAccount {
        private double interestRate;

        public SavingsAccount(String accNo, String name, double bal, double interestRate) {
            super(accNo, name, bal);
            this.interestRate = interestRate;
        }

        @Override
        public void withdraw(double amount) {
            if (amount > getBalance()) {
                System.out.println(" Withdrawal denied! Not enough balance.");
            } else {
                setBalance(getBalance() - amount);
                System.out.println("Withdrawn: " + amount);
            }
        }

        public void applyInterest() {
            double interest = getBalance() * interestRate / 100;
            setBalance(getBalance() + interest);
            System.out.println("Interest applied: " + interest);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount acc1 = new BankAccount("101", "John", 3000);
        SavingsAccount sav1 = new SavingsAccount("202", "Ram", 5000, 5);

        System.out.println("Choose Operation:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Apply Interest (Savings Account)");
        System.out.println("4. Show Details");
        System.out.println("5. Exit");

        while (true) {
            System.out.print("\nEnter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    sav1.withdraw(w);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double d = sc.nextDouble();
                    sav1.deposit(d);
                    break;

                case 3:
                    sav1.applyInterest();
                    break;

                case 4:
                    System.out.println("\n--- Bank Account ---");
                    acc1.printDetails();
                    System.out.println("\n--- Savings Account ---");
                    sav1.printDetails();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
