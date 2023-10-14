package stocktrader.server;

import java.time.LocalTime;

public class StockInformation {

    private Integer commandType;
    private Stock stock;
    private LocalTime purchaseDate;

    public StockInformation() {
    }

    public StockInformation(Integer commandType, Stock stock, LocalTime purchaseDate) {
        this.commandType = commandType;
        this.stock = stock;
        this.purchaseDate = purchaseDate;
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

    public LocalTime getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(LocalTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
