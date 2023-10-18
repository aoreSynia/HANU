package stocktrader.client;

import java.util.Scanner;

import stocktrader.server.StockServer;

public class StockClient {
    private StockServer stockServer;

    private String username;

    public StockClient() {
        stockServer = new StockServer();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        // Login process
        System.out.println("Welcome to the Stock Trader!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (stockServer.login(username, password)) {
            this.username = username;
            System.out.println("Login successful!");
            mainMenu();
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. List All Stocks");
            System.out.println("3. List Owned Stocks");
            System.out.println("4. Purchase Stock");
            System.out.println("5. Sell Stock");
            System.out.println("6. Next Day");
            System.out.println("7. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Check Balance
                    String balance = stockServer.getUserMoney();
                    System.out.println("Your balance: " + balance);
                    break;
                case 2:
                    // List All Stocks
                    String allStocks = stockServer.listAllStocks();
                    System.out.println(allStocks);
                    break;
                case 3:
                    // List Owned Stocks
                    String ownedStocks = stockServer.listOwnStocks();
                    System.out.println("Your owned stocks:\n" + ownedStocks);
                    break;
                case 4:
                    System.out.print("Enter the stock number you want to purchase: ");
                    int stockNumberToPurchase = scanner.nextInt();
                    System.out.print("Enter the quantity: ");
                    int quantityToPurchase = scanner.nextInt();
                
                    boolean purchaseResult = stockServer.purchase(stockNumberToPurchase, quantityToPurchase);
                
                    if (purchaseResult) {
                        System.out.println("Purchase successful.");
                    } else {
                        System.out.println("Purchase failed. Please check your input.");
                    }
                    break;
                
                case 5:
                    // List the owned stocks
                    String ownedStocksList = stockServer.listOwnStocks();
                    System.out.println("Your owned stocks:\n" + ownedStocksList);

                    System.out.print("Enter the stock number you want to sell: ");
                    int stockNumberToSell = scanner.nextInt();
                    System.out.print("Enter the quantity to sell: ");
                    int quantityToSell = scanner.nextInt();

                    boolean sellResult = stockServer.sellStock(stockNumberToSell, quantityToSell);

                    if (sellResult) {
                        System.out.println("Sell successful.");
                    } else {
                        System.out.println("Sell failed. Please check your input.");
                    }
                    break;
                case 6:
                    // Next Day
                    stockServer.nextDay();
                    System.out.println("A new day has started.");
                    break;
                case 7:
                    stockServer.updateStockInfoFile();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        StockClient client = new StockClient();
        client.run();
    }
}
