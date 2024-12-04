public class Account {
    private int number; // Account number
    private String name; // Account holder name
    private double balance; // Current balance
    private int nmuViewBalance; // Number of times balance is viewed
    private int numDeposits; // Number of deposits
    private int numWithdraws; // Number of withdrawals
    private int numTransfers; // Number of transfers
    private double totalInAccount; // Total money deposited
    private double totalOutAccount; // Total money withdrawn or transferred

    // Constants for fees
    private final double WITHDRAW_FEE = 0.01; // 1% withdrawal fee
    private final double TRANSFER_FEE = 0.025; // 2.5% transfer fee

    // Shared statistics
    public static int totalCharges; // Total fees collected
    public static int totalViewBalance; // Total times balance is viewed
    public static int totalDeposits; // Total deposits across all accounts
    public static int totalWithdraws; // Total withdrawals across all accounts
    public static int totalTransfers; // Total transfers across all accounts

    // Constructor to initialize account details
    Account(int number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    // Helper method to check if the requested amount is available
    private boolean isAvailable( double amount){

        if( amount > 0 && amount <= balance)
            return true;
        else
            return false;
    }
    
    // Method to deposit money into the account
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("The deposit of " + amount + " has been successfully completed.");
            totalInAccount += amount;
            numDeposits++;
            totalDeposits++;
            return true;
        } else {
            System.out.println("Error: You can't deposit 0 amount or less!!");
            return false;
        }
    }

    // Method to withdraw money from the account
    public boolean withdraw(double amount) {
        double withdrawCharge = amount * WITHDRAW_FEE;
        if (isAvailable(amount + withdrawCharge)) {
            balance -= amount + withdrawCharge;
            totalCharges += withdrawCharge;
            System.out.println("The withdrawal of " + amount + " has been successfully completed.");
            numWithdraws++;
            totalWithdraws++;
            totalOutAccount += amount + withdrawCharge;
            return true;
        } else {
            System.out.println("Error: The amount isn't available or is 0 or less.");
            return false;
        }
    }

    // Method to transfer money to another account
    public void transfer(Account obj, double amount) {
        double transferCharge = amount * TRANSFER_FEE;
        if (isAvailable(amount + transferCharge)) {
            balance -= amount + transferCharge;
            totalOutAccount += amount + transferCharge;
            obj.balance += amount;
            obj.totalInAccount += amount;
            totalCharges += transferCharge;
            numTransfers++;
            totalTransfers++;
            System.out.println("The transfer of " + amount + " has been successfully completed.");
        } else 
            System.out.println("Error: The amount isn't available or is 0 or less.");
        
    }

    // Display current balance
    public void displayBalance() {
        System.out.println("Account Name: " + name);
        System.out.println("Account number: " + number);
        System.out.printf("Account Balance: %.2f\n", balance);
        nmuViewBalance++;
        totalViewBalance++;
    }

    // Display account statistics
    public void displayStatistics() {
        System.out.println("----------");
        System.out.println("STATISTICS SUMMARY");
        System.out.println("----------");
        System.out.println("Number of balance views: " + nmuViewBalance);
        System.out.println("Number of deposits: " + numDeposits);
        System.out.println("Number of transfers: " + numTransfers);
        System.out.println("Number of withdrawals: " + numWithdraws);
        System.out.printf("Total money in account: %.2f\n", totalInAccount);
        System.out.printf("Total money out of account: %.2f\n", totalOutAccount);
        System.out.println("-----------------------------");
    }
}