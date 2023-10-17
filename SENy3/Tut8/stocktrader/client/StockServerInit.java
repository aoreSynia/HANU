package stocktrader.client;

import stocktrader.server.StockServer;

public class StockServerInit {

    public static void main(String[] args) {
        // Khởi tạo đối tượng StockServer
        StockServer stockServer = new StockServer();

        // Đăng nhập
        boolean loggedIn = stockServer.login("nam", "pass1");

        if (loggedIn) {
            System.out.println("Logged in successfully!");

            // Hiển thị danh sách tất cả cổ phiếu
            String allStocks = stockServer.listAllStocks();
            System.out.println("All Stocks:\n" + allStocks);

            // Hiển thị danh sách cổ phiếu của người dùng
            String ownStocks = stockServer.listOwnStocks();
            System.out.println("Your Stocks:\n" + ownStocks);

            // Thực hiện mua bán cổ phiếu, sau đó hiển thị lại danh sách cổ phiếu của người dùng
            stockServer.purchase(1, 10); // Mua 10 cổ phiếu
            ownStocks = stockServer.listOwnStocks();
            System.out.println("Your Stocks after purchase:\n" + ownStocks);

            stockServer.sellStock(1, 5); // Bán 5 cổ phiếu
            ownStocks = stockServer.listOwnStocks();
            System.out.println("Your Stocks after selling:\n" + ownStocks);
        } else {
            System.out.println("Login failed.");
        }
    }
}
