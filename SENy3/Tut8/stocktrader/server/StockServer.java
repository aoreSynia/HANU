package stocktrader.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockServer {
    private ArrayList<User> users;
    private ArrayList<Stock> stocks;
    private ArrayList<StockInformation> userStocks;
    private ArrayList<StockInformation> userHistory;
    private Double userMoney;
    private boolean isLoggedIn;
    public LocalTime currentDate; 
    private String username_loggedin;

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
    public void readUserData(String userDataFileName, String historyFileName) {
        userStocks = new ArrayList<>();
        userHistory = new ArrayList<>();
        
        // Đọc dữ liệu từ tệp "username.txt"
        try (BufferedReader reader = new BufferedReader(new FileReader(userDataFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    if (parts[0].equalsIgnoreCase("money")) {
                        userMoney = Double.parseDouble(parts[1].trim());
                    } else if (parts.length == 3) {
                        String stockName = parts[0].trim();
                        Double stockPrice = Double.parseDouble(parts[1].trim());
                        Integer stockQuantity = Integer.parseInt(parts[2].trim());
    
                        Stock stock = new Stock(stockName, stockPrice, stockQuantity);
                        userStocks.add(new StockInformation(1, stock, LocalDate.now()));
                    }
                    // Xử lý các thông tin khác của người dùng nếu cần.
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ phù hợp trong ứng dụng của bạn
        }
    
        // Đọc dữ liệu từ tệp "username_history.txt"
        try (BufferedReader reader = new BufferedReader(new FileReader(historyFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 5 && parts[0].equalsIgnoreCase("buy")) {
                    String stockName = parts[1].trim();
                    Double stockPrice = Double.parseDouble(parts[2].trim());
                    Integer stockQuantity = Integer.parseInt(parts[3].trim());
                    // Xử lý ngày mua stock từ parts[4] nếu cần.
    
                    Stock stock = new Stock(stockName, stockPrice, stockQuantity);
                    userHistory.add(new StockInformation(1, stock, LocalDate.now()));
                }
                // Xử lý các loại giao dịch khác nếu cần.
            }
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ phù hợp trong ứng dụng của bạn
        }
    }
    
    // login method.
    public boolean login(String username, String password){
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
    
                // Thêm đoạn code để đọc dữ liệu từ các tệp username.txt và "username"_history.txt
                String userDataFileName = "data\\userdata\\" + username + ".txt";
                String userHistoryFileName = "data\\userdata\\" + username + "_history.txt";
                readUserData(userDataFileName, userHistoryFileName);
                username_loggedin = username;
                return true;
            } 
        }
        return false;
        
    }
        

    public String getUserMoney(){
        String money = userMoney.toString();
        return money;
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
    
        for (int i = 0; i < userStocks.size(); i++) {
            StockInformation stockInfo = userStocks.get(i);
            Stock stock = stockInfo.getStock();
            printer.append(String.format("Stock %d: %s (Price: %.2f, Quantity: %d)\n",
                    i + 1, stock.getName(), stock.getPrice(), stock.getQuantity()));
        }
    
        return printer.toString();
    }
    

//     public boolean purchase(int stockNo, int quantity) {
//     if (stockNo > 0 && stockNo <= stocks.size()) {
//         Stock selectedStock = stocks.get(stockNo - 1);

//         if (selectedStock.getQuantity() >= quantity) {
//             double totalValue = selectedStock.getPrice() * quantity;

//             if (userMoney >= totalValue) {
//                 // Cập nhật số lượng cổ phiếu đã mua
//                 selectedStock.setQuantity(selectedStock.getQuantity() - quantity);

//                 // Cập nhật vào ví của người dùng
//                 userMoney -= totalValue;

//                 // Lưu thông tin giao dịch
//                 userHistory.add(new StockInformation(3, selectedStock, LocalDate.now()));
                
//                 // Sử dụng storeInformation để lưu thông tin giao dịch vào lịch sử
//                 String userDataFileName = "data\\userdata\\" + username_loggedin + "_history.txt";
//                 storeInformation(userDataFileName, userHistory.get(userHistory.size() - 1));

//                 return true;
//             }
//         }
//     }

//     return false;
// }

//     public boolean sellStock(int stockNo, int quantity) {
//         if (stockNo > 0 && stockNo <= userStocks.size()) {
//             StockInformation selectedStockInfo = userStocks.get(stockNo - 1);
//             Stock selectedStock = selectedStockInfo.getStock();

//             if (selectedStockInfo.getQuantity() >= quantity) {
//                 double totalValue = selectedStock.getPrice() * quantity;
//                 userMoney += totalValue;

//                 // Cập nhật số lượng cổ phiếu đã bán
//                 selectedStockInfo.setQuantity(selectedStockInfo.getQuantity() - quantity);

//                 // Nếu người dùng đã bán hết cổ phiếu, loại bỏ nó khỏi danh sách userStocks
//                 if (selectedStockInfo.getQuantity() == 0) {
//                     userStocks.remove(selectedStockInfo);
//                 }

//                 // Lưu thông tin giao dịch
//                 userHistory.add(new StockInformation(4, selectedStock, LocalDate.now(), quantity));
                
//                 // Sử dụng storeInformation để lưu thông tin giao dịch vào lịch sử
//                 String userDataFileName = "data\\userdata\\" + username_loggedin + "_history.txt";
//                 storeInformation(userDataFileName, userHistory.get(userHistory.size() - 1));

//                 return true;
//             }
//         }

//         return false;
//     }
    public boolean purchase(int stockNo, int quantity) {
        if (stockNo > 0 && stockNo <= stocks.size()) {
            Stock selectedStock = stocks.get(stockNo - 1);

            if (selectedStock.getQuantity() >= quantity) {
                double totalValue = selectedStock.getPrice() * quantity;

                if (userMoney >= totalValue) {
                    // Cập nhật số lượng cổ phiếu đã mua
                    selectedStock.setQuantity(selectedStock.getQuantity() - quantity);

                    boolean stockOwned = false;
                    for (StockInformation stockInfo : userStocks) {
                        if (stockInfo.getStock().getName().equals(selectedStock.getName())) {
                            stockInfo.setQuantity(stockInfo.getQuantity() + quantity);
                            stockOwned = true;
                            break;
                        }
                    }

                    if (!stockOwned) {
                        userStocks.add(new StockInformation(3, selectedStock, LocalDate.now(), quantity));
                    }

                    // Cập nhật ví của người dùng
                    userMoney -= totalValue;

                    // Lưu thông tin giao dịch
                    userHistory.add(new StockInformation(3, selectedStock, LocalDate.now(), quantity));
                    
                    // Lưu thông tin giao dịch vào lịch sử
                    String userDataFileName = "data\\userdata\\" + username_loggedin + "_history.txt";
                    storeInformation(userDataFileName, userHistory.get(userHistory.size() - 1));
                    updateUserFile(username_loggedin, userMoney, userStocks);

                    return true;
                }
            }
        }

        return false;
    }

    public boolean sellStock(int stockNo, int quantity) {
        if (stockNo > 0 && stockNo <= userStocks.size()) {
            StockInformation selectedStockInfo = userStocks.get(stockNo - 1);
            Stock selectedStock = selectedStockInfo.getStock();

            if (selectedStockInfo.getQuantity() >= quantity) {
                double totalValue = selectedStock.getPrice() * quantity;

                selectedStockInfo.setQuantity(selectedStockInfo.getQuantity() - quantity);

                // If all units of the stock are sold, remove it from userStocks
                if (selectedStockInfo.getQuantity() == 0) {
                    userStocks.remove(selectedStockInfo);
                }

                // Cập nhật ví của người dùng
                userMoney += totalValue;

                // Lưu thông tin giao dịch
                userHistory.add(new StockInformation(4, selectedStock, LocalDate.now(), quantity));
                
                // Lưu thông tin giao dịch vào lịch sử
                String userDataFileName = "data\\userdata\\" + username_loggedin + "_history.txt";
                storeInformation(userDataFileName, userHistory.get(userHistory.size() - 1));
                updateUserFile(username_loggedin, userMoney, userStocks);

                return true;
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


    public String checkBalance() {
        Double stockValue = 0.0;
        for (StockInformation stockInfo : userStocks) {
            Stock selectedStock = stockInfo.getStock();
            stockValue = stockValue + (selectedStock.getPrice() * selectedStock.getQuantity());
        }
        Double totalBalance = stockValue + userMoney;

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");  // Format to two decimal places with commas

        return decimalFormat.format(totalBalance);  // Convert to millions and apply formatting
    }
    
    public String getPrice(){
        StringBuilder priceInfo = new StringBuilder("Current Stock Prices:\n");
        for (Stock stock : getCurrentPrice("data\\StockInfo.txt")) {
            priceInfo.append(String.format("%s (Price: %.2f)\n", stock.getName(), stock.getPrice()));
        }
        return priceInfo.toString();
    }

    private ArrayList<Stock> getCurrentPrice(String priceFileName) {
        ArrayList<Stock> currentPrices = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(priceFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String stockName = parts[0].trim();
                    Double stockPrice = Double.parseDouble(parts[1].trim());
    
                    for (Stock stock : stocks) {
                        if (stock.getName().equals(stockName)) {
                            stock.setPrice(stockPrice);
                            currentPrices.add(stock);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    
        return currentPrices;
    }
    // Phương thức storeInformation dùng để lưu thông tin giao dịch
    // Phương thức storeInformation dùng để lưu thông tin giao dịch
    // Phương thức storeInformation dùng để lưu thông tin giao dịch
    private void storeInformation(String historyFileName, StockInformation info) {
        try (FileWriter writer = new FileWriter(historyFileName, true)) {
            String stockName = info.getStock().getName();
            Double stockPrice = info.getStock().getPrice();
            Integer stockQuantity = info.getQuantity();
            String formattedDate = info.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
            String commandType = info.getCommandType() == 3 ? "buy" : "sell"; // Xác định loại giao dịch
    
            String record = String.format("\n%s %s %.2f %d %s", commandType, stockName, stockPrice, stockQuantity, formattedDate);
            writer.write(record);
            // Đảm bảo dữ liệu được lưu vào tệp và đóng tệp sau khi ghi
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách phù hợp trong ứng dụng của bạn
        }
    }
    

    private void updateUserFile(String username, double money, List<StockInformation> userStocks) {
        try (FileWriter writer = new FileWriter("data\\userdata\\" + username + ".txt", false)) {
            // Write the user's money
            writer.write("Money " + money + "\n");

            // Write the user's stocks
            for (StockInformation stockInfo : userStocks) {
                Stock stock = stockInfo.getStock();
                writer.write(stock.getName() + " " + stock.getPrice() + " " + stockInfo.getQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }





}
