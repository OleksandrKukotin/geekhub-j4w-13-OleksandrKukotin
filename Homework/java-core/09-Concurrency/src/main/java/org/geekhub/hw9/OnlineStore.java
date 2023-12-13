package org.geekhub.hw9;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class OnlineStore {

    private final ExecutorService saleService = Executors.newSingleThreadExecutor();
    private final Map<String, Integer> positions = new ConcurrentHashMap<>();
    private final AtomicInteger totalSales = new AtomicInteger(0);

    public Future<Boolean> purchase(String positionName, int quantity) {
        return saleService.submit(() -> {
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
        });
    }

    public void addProduct(String positionName, int amount) {
        positions.put(positionName, amount);
    }

    public int getProductQuantity(String positionName) {
        return positions.get(positionName);
    }

    public int getTotalSales() {
        return totalSales.get();
    }
}
