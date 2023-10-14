package stocktrader.server;

import java.nio.file.AccessDeniedException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class StockServer {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Stock> stocks = new ArrayList<Stock>();
    private ArrayList<StockInformation> userStocks = new ArrayList<StockInformation>();
    private ArrayList<StockInformation> userHistory = new ArrayList<StockInformation>();
    public Double userMoney;
    private boolean isLoggedIn;
    public LocalTime currentDate; 

    public StockServer() {
        users.add(new User("Kian", "stay123"));
        stocks.add(new Stock("Stock A", 100.0, 1000));
        stocks.add(new Stock("Stock B", 50.0, 500));
        stocks.add(new Stock("Stock C", 75.0, 750));
        userMoney = 10000000.0;
    }

    public class AccessDeniedException extends Exception {
        public AccessDeniedException() { 
            super("Access Denied!");
        }
        
        public AccessDeniedException(String message) {
            super(message);
        }
    }

    // login method.
    public boolean login(String username, String password) throws AccessDeniedException {
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                System.out.println("Welcome back " + username + "!");
                return true;
            }
        }
        throw new AccessDeniedException();
    }
    

    public String listAllStocks() {
        StringBuilder printer = new StringBuilder();

        // Check if there are no stocks available
        if (stocks.isEmpty()) {
            printer.append("No stocks available.\n");
        } else {
            printer.append("List of all stocks:\n");

            // Iterate through the list of stocks and build the string
            int stockNo = 1;
            for (Stock stockItem : stocks) {
                printer.append(String.format("Stock %d: %s (Price: %.2f, Quantity: %d)\n",
                        stockNo, stockItem.getName(), stockItem.getPrice(), stockItem.getQuantity()));
                stockNo++;
            }
        }

        return printer.toString();
    }
    public String listOwnStocks() {
        StringBuilder printer = new StringBuilder();
        
        for (StockInformation stockInfo : userStocks) {
            Stock stock = stockInfo.getStock();
            printer.append(String.format("Stock %d: %s (Price: %.2f, Quantity: %d)\n", 
                stock.getName(), stock.getPrice(), stock.getQuantity()));
        }

        // for (int i = 0; i < userStocks.size(); i++) {
        //     StockInformation stockInfo = userStocks.get(i);
        //     Stock stock = stockInfo.getStock();
        //     result.append(String.format("Stock %d: %s (Price: %.2f, Quantity: %d)\n", 
        //         i + 1, stock.getName(), stock.getPrice(), stock.getQuantity()));
        // }
        
        return printer.toString();
    }
    
    
    public boolean purchase(int stockNo, int quantity) {
        // implement the logic @.@
        if (stockNo > 0 && stockNo <= stocks.size() ) {
            Stock selectedStock = stocks.get(stockNo - 1);

            if (selectedStock.getQuantity() >= quantity) {
                double totalValue = selectedStock.getPrice() * quantity;
                
                if (userMoney >= totalValue) {
                    // cập nhật lại cái nịt cho nhà đầu tư
                    userMoney -= totalValue;
                    // cập nhật lại số lượng của stock được chọn
                    selectedStock.setQuantity(
                        selectedStock.getQuantity() - quantity
                    );
                    // cập nhật vào ví của nhà đầu tư
                    userStocks.add(new StockInformation(1, selectedStock, LocalTime.now()));
                    return true;
                } 
            }
        }

        return false;
    }
    
    
    public void nextDay() {
        Random random = new Random();
        for (Stock item : stocks) {
            double priceChange = (random.nextDouble() - 0.5) * 10; // Generate a random price change
            Double newPrice = item.getPrice() + priceChange;
            item.setPrice(newPrice);
        }
    }

    public Double checkBalance() {
        double stockValue = 0.0; 
        for (StockInformation stockInfo : userStocks) {
            Stock selectedStock = stockInfo.getStock();
            stockValue = stockValue +(selectedStock.getPrice() * selectedStock.getQuantity());
        }
        return Double(stockValue + userMoney);
    }
}
