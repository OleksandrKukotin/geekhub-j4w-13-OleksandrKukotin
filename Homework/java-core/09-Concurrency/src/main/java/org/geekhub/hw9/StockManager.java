package org.geekhub.hw9;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StockManager {

    private final ScheduledExecutorService manager;
    private final OnlineStore onlineStore;
    private final int restockingAmount;

    public StockManager(OnlineStore onlineStore, int restockingAmount, int restockingInterval) {
        this.manager = Executors.newScheduledThreadPool(8);
        this.onlineStore = onlineStore;
        this.restockingAmount = restockingAmount;

        manager.scheduleAtFixedRate(this::restock, restockingInterval, restockingInterval, TimeUnit.SECONDS);
    }

    public void restock() {
        onlineStore.addProduct("EcoFlow", restockingAmount);
        onlineStore.addProduct("Snickers", restockingAmount);
    }

    public void shutdown() {
        manager.shutdown();
    }
}
