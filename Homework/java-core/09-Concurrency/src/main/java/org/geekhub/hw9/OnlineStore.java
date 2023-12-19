package org.geekhub.hw9;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OnlineStore {

    private final ExecutorService saleService;
    private final Map<String, Integer> positions;
    private final AtomicInteger totalSales;
    private final Lock purchaseLock;

    public OnlineStore(ExecutorService saleService) {
        this.saleService = saleService;
        this.positions = new ConcurrentHashMap<>();
        this.totalSales = new AtomicInteger(0);
        this.purchaseLock = new ReentrantLock();
    }

    public Future<Boolean> purchase(String positionName, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity should be non-negative");
        }
        return saleService.submit(() -> {
            purchaseLock.lock();
            try {
                if (positions.containsKey(positionName)) {
                    int productQuantity = positions.get(positionName);
                    int remaining = productQuantity - quantity;
                    if (remaining >= 0) {
                        positions.replace(positionName, remaining);
                        totalSales.getAndAdd(quantity);
                        return true;
                    }
                }
                return false;
            } finally {
                purchaseLock.unlock();
            }
        });
    }

    public void addProduct(String positionName, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Quantity should be non-negative");
        }
        positions.merge(positionName, amount, Integer::sum);
    }

    public int getProductQuantity(String positionName) {
        return positions.get(positionName);
    }

    public int getTotalSales() {
        return totalSales.get();
    }
}
