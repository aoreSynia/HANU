package stocktrader.server;

import java.time.LocalDate;

public class StockInformation {

    private Integer commandType;
    private Stock stock;
    private LocalDate purchaseDate;
    // private LocalTime purchaseDate;

    public StockInformation() {
    }

    public StockInformation(Integer commandType, Stock stock, LocalDate purchaseDate) {
        this.commandType = commandType;
        this.stock = stock;
        this.purchaseDate = purchaseDate;
    }
    public StockInformation(Integer commandType, Stock stock, LocalDate purchaseDate, Integer quantity) {
        this.commandType = commandType;
        this.stock = new Stock(stock.getName(), stock.getPrice(), quantity);
        this.purchaseDate = purchaseDate;
        // this.stock.setQuantity(quantity);
    }

    public Integer getCommandType() {
        return this.commandType;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public int getQuantity() {
        return stock.getQuantity();
    }
    public void setQuantity(Integer quantity) {
        stock.setQuantity(quantity);
    }

}
