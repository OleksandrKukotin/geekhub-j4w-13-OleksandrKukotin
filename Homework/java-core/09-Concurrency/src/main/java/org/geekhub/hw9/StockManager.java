package org.geekhub.hw9;

public class StockManager {

    private final OnlineStore onlineStore;
    private int restockingValue;
    private int restockingPeriod;

    public StockManager(OnlineStore onlineStore, int restockingValue, int restockingPeriod) {
        this.onlineStore = onlineStore;
        this.restockingValue = restockingValue;
        this.restockingPeriod = restockingPeriod;
    }
}
