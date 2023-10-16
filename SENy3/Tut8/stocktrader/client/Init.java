package stocktrader.client;

import java.time.LocalTime;

import stocktrader.server.StockServer;
import stocktrader.server.StockServer.AccessDeniedException;

public class Init {
    public static void main(String[] args) {
        StockServer sv = new StockServer();
        
        // Initialize currentDate
        sv.currentDate = LocalTime.now();
        // Logging in
        try {

            sv.login("nam", "pass1");
            String stockList = sv.listAllStocks();
            System.out.println(stockList);

            sv.purchase(2, 100);
            
            String updatedStockList = sv.listAllStocks();
            System.out.println(updatedStockList);
            System.out.println("money left : " + sv.checkBalance());
            sv.nextDay();
            String afterDayStockList = sv.listAllStocks();
            System.out.println("Stock prices have been updated after one day:\n" + afterDayStockList);

            System.out.println("User total balance :" + sv.checkBalance());
        } catch (AccessDeniedException e) {
            System.err.println("Access denied: " + e.getMessage());
        }
    }

}
