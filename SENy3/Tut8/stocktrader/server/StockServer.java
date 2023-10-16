package stocktrader.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StockServer {
    private ArrayList<User> users;
    private ArrayList<Stock> stocks;
    private ArrayList<StockInformation> userStocks;
    private ArrayList<StockInformation> userHistory;
    private boolean isLoggedIn;
    public LocalTime currentDate; 

    public StockServer() {
        users = readUserList("data\\userlist.txt");
        stocks = readStockInfo("data\\StockInfo.txt");
    }
    //read user's accounts
    public ArrayList<User> readUserList(String userListFileName) {
        ArrayList<User> users = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(userListFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    User user = new User(username, password);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            
        }
        
        return users;
    }
    // read Stocks method
    public ArrayList<Stock> readStockInfo(String stocks_fileName){
        ArrayList<Stock> stocks = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(stocks_fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String stockName = parts[0].trim();
                    Double stockPrice = Double.parseDouble(parts[1].trim());
                    Integer stockQuantity = Integer.parseInt(parts[2].trim()) ;

                    Stock stock = new Stock(stockName, stockPrice, stockQuantity);
                    stocks.add(stock);
                }
            }
        } catch (IOException e) {
            
        }
        
        return stocks;
    }
    //read userdata
    // public ArrayList<StockInformation> readUserData(String userData_fileName, String history_fileName) {
    //     int lastDotIndex_userStock = userData_fileName.lastIndexOf(".");
    //     int lastDotIndex_userHistory = history_fileName.lastIndexOf("_");
    //     int cur = 0;

    //     ArrayList<StockInformation> userStocks = new ArrayList<StockInformation>() ;

    //     try (BufferedReader reader = new BufferedReader(new FileReader(userData_fileName))) {
    //         String username = userData_fileName.substring(0, lastDotIndex_userStock);
    //         String line;
    
    //         while ((line = reader.readLine()) != null) {
    //             String[] parts = line.split(" ");
    
    //             if (parts.length >= 2) {
    //                 if (parts[0].equalsIgnoreCase("money")) {
    //                     Double userMoney = Double.parseDouble(parts[1].trim());
    //                     // Find the user with the matching username and set their userMoney
    //                     for (User user : users) {
    //                         if (user.getUsername().equalsIgnoreCase(username)) {
    //                             user.setUserMoney(userMoney);
    //                         }
    //                     }
    //                 } else if (parts.length == 3) {
    //                     String stockName = parts[0].trim();
    //                     Double stockPrice = Double.parseDouble(parts[1].trim());
    //                     Integer stockQuantity = Integer.parseInt(parts[2].trim()) ;

    //                     Stock stock = new Stock(stockName, stockPrice, stockQuantity);
    //                     userStocks.add(stock);
    //                 } 
    //                 // Here, you can handle other user-specific data if needed.
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace(); // Handle the exception properly in your application
    //     }
    // }
    public void readUserData(String userDataFileName, String historyFileName) {
        userStocks = new ArrayList<>();
        userHistory = new ArrayList<>();
        User pointer -
    
        // Đọc dữ liệu từ tệp "username.txt"
        try (BufferedReader reader = new BufferedReader(new FileReader(userDataFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    if (parts[0].equalsIgnoreCase("money")) {
                        Double userMoney = Double.parseDouble(parts[1].trim());
                        setUserMoney(userMoney);
                    } else if (parts.length == 3) {
                        String stockName = parts[0].trim();
                        Double stockPrice = Double.parseDouble(parts[1].trim());
                        Integer stockQuantity = Integer.parseInt(parts[2].trim());
    
                        Stock stock = new Stock(stockName, stockPrice, stockQuantity);
                        userStocks.add(new StockInformation(1, stock, LocalTime.now()));
                    }
                    // Xử lý các thông tin khác của người dùng nếu cần.
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ phù hợp trong ứng dụng của bạn
        }
    
        // Đọc dữ liệu từ tệp "username_history.txt"
        try (BufferedReader reader = new BufferedReader(new FileReader(historyFileName)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 5 && parts[0].equalsIgnoreCase("buy")) {
                    String stockName = parts[1].trim();
                    Double stockPrice = Double.parseDouble(parts[2].trim());
                    Integer stockQuantity = Integer.parseInt(parts[3].trim());
                    // Xử lý ngày mua stock từ parts[4] nếu cần.
    
                    Stock stock = new Stock(stockName, stockPrice, stockQuantity);
                    userHistory.add(new StockInformation(1, stock, LocalTime.now()));
                }
                // Xử lý các loại giao dịch khác nếu cần.
            }
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ phù hợp trong ứng dụng của bạn
        }
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
    
                // Thêm đoạn code để đọc dữ liệu từ các tệp username.txt và "username"_history.txt
                String userDataFileName = "data\\userdata\\" + username + ".txt";
                String userHistoryFileName = "data\\userdata\\" + username + "_history.txt";
                userStocks = readUserData(userDataFileName, userHistoryFileName);
                
                return isLoggedIn = true;
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
        Double stockValue = 0.0; 
        for (StockInformation stockInfo : userStocks) {
            Stock selectedStock = stockInfo.getStock();
            stockValue = stockValue +(selectedStock.getPrice() * selectedStock.getQuantity());
        }
        return stockValue + userMoney;
    }
}
