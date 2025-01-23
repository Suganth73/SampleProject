/* TITILE				: Investment Platofrom

* AUTHOR				: Suganth J

* CREATED ON			: 15/01/2025

* LAST MODIFIED DATE	: 17/01/2025

* REVIEWED BY			:

* REVIEWED ON			:*/

package User.Info.InvestmentPatform;

import java.util.Scanner;

public class Platform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();
        UserService userService = new UserService(dbManager);
        InvestmentService investmentService = new InvestmentService(dbManager);

        while (true) {
            System.out.println("\n=== Investment Platform Menu ===");
            System.out.println("1. Register User");
            System.out.println("2. Deposit Money");
            System.out.println("3. Invest Money");
            System.out.println("4. View Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 : {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        userService.registerUser(username);
                        scanner.nextLine();
                    }
                    case 2 : {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        userService.deposit(username, amount);
                        scanner.nextLine();
                    }
                    case 3 : {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter investment amount: ");
                        double amount = scanner.nextDouble();
                        investmentService.invest(username, amount);
                        scanner.nextLine();
                    }
                    case 4 : {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        userService.viewBalance(username);
                        scanner.nextLine();
                    }
                    
                    case 5 : {
                        System.out.println("Exiting the platform. Goodbye!");
                        scanner.close();
                        return;
                    }
                    default : System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.next(); 
            }
        }
    }
}























//System.out.println("5. View Investment History");

//case 5 : {
//    System.out.print("Enter username: ");
//    String username = scanner.nextLine();
//    investmentService.viewInvestmentHistory(username);
//    scanner.nextLine();
//}