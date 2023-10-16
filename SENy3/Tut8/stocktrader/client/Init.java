package stocktrader.client;

import stocktrader.server.StockServer;

public class Init {
    public static void main(String[] args) {
        // Create a StockServer instance
        StockServer stockServer = new StockServer();

        // Simulate a user login (replace "nam" and "password" with actual credentials)
        String username = "nam";
        String password = "pass1";

        try {
            if (stockServer.login(username, password)) {
                // Display a welcome message
                System.out.println("Welcome back " + username + "!");
                
                // List all available stocks
                System.out.println(stockServer.listAllStocks());
                System.out.println("User money: " + stockServer.getUserMoney());
                
                // List user's own stocks
                System.out.println(stockServer.listOwnStocks());
                
                // Simulate a stock purchase (replace stockNo and quantity with actual values)
                int stockNo = 1; // Replace with the stock number you want to purchase
                int quantity = 10; // Replace with the quantity you want to purchase
                boolean purchased = stockServer.purchase(stockNo, quantity);
                if (purchased) {
                    System.out.println("Successfully purchased stock.");
                } else {
                    System.out.println("Failed to purchase stock.");
                }

                // Simulate the next day's stock price change
                stockServer.nextDay();

                // Calculate and display the user's total balance
                System.out.println("Your total balance: " + stockServer.checkBalance());
            } else {
                System.out.println("Login failed. Please check your credentials.");
            }
        } catch (stocktrader.server.StockServer.AccessDeniedException e) {
            System.out.println(e.getMessage());
        }
    }
}
