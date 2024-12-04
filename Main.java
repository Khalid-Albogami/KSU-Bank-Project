import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // Initialize accounts
    static Account nasserAccount = new Account(15300, "Nasser", 97_478.23);
    static Account khalidAccount = new Account(15200, "Khalid", 115_000.74);
    static Account abdulazizAccount = new Account(15100, "Abdulaziz", 247_388.11);
    public static void main(String[] args) {
            
    
        while (true) {
                
                System.out.println("1) Administrator");
                System.out.println("2) Khalid's Account");
                System.out.println("3) Nasser's Account");
                System.out.println("4) Abdulaziz's Account");
                System.out.println("5) Exit");
    
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                System.out.println();
                
                // Handle user choice
                if (choice == 1)
                printAdministratorList();
                else if(choice == 2)
                printAccountList(khalidAccount);
                else if(choice == 3)
                printAccountList(nasserAccount);
                else if(choice == 4)
                printAccountList(abdulazizAccount);
                else if(choice == 5) {
                System.out.println("Thank you for using KSU Bank");
                System.exit(0);
                }
                else
                System.out.println("Invalid choice.");
        }
    }

    // Display account menu for a specific user
    public static void printAccountList(Account obj){

        System.out.println("1) View Balance ");
        System.out.println("2) Deposit Money ");
        System.out.println("3) Withdraw Money ");
        System.out.println("4) Transfer Money ");
        System.out.println();
        System.out.print("Choose from the given list: ");

        double amount;
        while(true) {
            int choice = scanner.nextInt();
            if (choice == 1) {
                // Display balance
                obj.displayBalance();
                returnToAccountMenu(obj);
                break;
            }
            
            else if(choice == 2) {
                // Deposit money
                do{
                    System.out.print("How much do you want to Deposit: ");
                    amount = scanner.nextDouble();
                }while( !obj.deposit(amount) );
                
                returnToAccountMenu(obj);  
                break;
            }
            else if(choice == 3) {
                // Withdraw money
                do {
                    System.out.print("How much do you want to Withdraw: ");
                    amount = scanner.nextDouble();
                } while (!obj.withdraw(amount));
                
                returnToAccountMenu(obj);
                break;
            }
            else if(choice == 4) {
                // Transfer money
                System.out.print("How much do you want to Transfer: ");
                amount = scanner.nextDouble();
                transferTo(amount, obj);  
                returnToAccountMenu(obj);          
                break;
            } 
            else
                System.out.println("Invalid choice.");

            System.out.print("please enter a correct choice: ");
        }

    }

    // Handle money transfer
    public static void transferTo(double amount , Account obj){
        System.out.println("Who would you like to transfer to? ");

        // Display possible accounts for transfer
        int choice = 0;
        while(choice != 1 && choice !=2){
            if(obj == khalidAccount){
        
                System.out.println("1) Nasser");
                System.out.println("2) Abdulaziz");
                choice = scanner.nextInt();

                if(choice == 1){
                    obj.transfer(nasserAccount, amount);
                }
                else if ( choice == 2){
                    obj.transfer(abdulazizAccount, amount);
                }
                else
                    System.out.println("Invalid choice.");
                    
             } 
             else if ( obj == nasserAccount){

                System.out.println("1) Khalid");
                System.out.println("2) Abdulaziz");
                choice = scanner.nextInt();

                if(choice == 1)
                    obj.transfer(khalidAccount, amount);
                else if ( choice == 2)
                    obj.transfer(abdulazizAccount, amount);
                else
                    System.out.println("Invalid choice.");
             }
             else{

                System.out.println("1) Khalid");
                System.out.println("2) Nasser");
                
                if(choice == 1)
                    obj.transfer(khalidAccount, amount);
                else if ( choice == 2)
                    obj.transfer(nasserAccount, amount);
                else
                    System.out.println("Invalid choice.");

             }
         }  
    }      

    // Ask user if they want to return to the account menu
    public static void returnToAccountMenu(Account obj){
        System.out.println("-------");
        System.out.println("Do you want to return to your main menu:");
        System.out.println("1) Yes");
        System.out.println("2) No");

        while(true) {
            int yesOrNo = scanner.nextInt();
            if (yesOrNo == 1) {
                printAccountList(obj);
                break;
            } else if (yesOrNo == 2) {
                obj.displayStatistics();
                break;
            }else
                System.out.println("Invalid choice.");
            System.out.print("please enter a correct choice: ");
        }
        
    }

    // Display administrator options
    public static void printAdministratorList(){
        while(true) {

            System.out.println("---------");
            System.out.println("1) View total profit ");
            System.out.println("2) Most popular operation ");
            System.out.println("3) Exit and return to the main menu ");
            System.out.println();
            System.out.print("Choose from the given list: ");
            int choice = scanner.nextInt();
            System.out.println("-----------");


            if (choice == 1) {
                System.out.println("Total Profit: " + Account.totalCharges );
            } else if(choice == 2) {
                mostPopularOperation();
            } else if(choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
                System.out.println("Please enter a correct choice: ");
            }
        }
    }

    // Display the most popular operation
    public static void mostPopularOperation() {
        
        int totalDeposits = Account.totalDeposits;
        int totalWithdraws = Account.totalWithdraws;
        int totalTransfers = Account.totalTransfers;
        
        if (totalDeposits > totalWithdraws && totalDeposits > totalTransfers) {
            System.out.println("Deposit is the most popular operation.");

        } else if (totalWithdraws > totalDeposits && totalWithdraws > totalTransfers) {
            System.out.println("Withdraw is the most popular operation.");

        } else if (totalTransfers > totalDeposits && totalTransfers > totalWithdraws) {
            System.out.println("Transfer is the most popular operation.");

        } else if (totalDeposits == totalWithdraws && totalDeposits == totalTransfers) {
            System.out.println("Deposit, Withdraw, and Transfer are equally popular.");

        } else if (totalDeposits == totalWithdraws) {
            System.out.println("Deposit and Withdraw are the most popular operations.");

        } else if (totalDeposits == totalTransfers) {
            System.out.println("Deposit and Transfer are the most popular operations.");

        } else {
            System.out.println("Withdraw and Transfer are the most popular operations.");
        }
    }
}
