package org.geekhub.hw9;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineStore {

    private Map<String, Integer> positions = new ConcurrentHashMap<>();

    public void addProduct(String positionName, int amount) {
        positions.put(positionName, amount);
    }

    public int getProductQuantity(String positionName) {
        return positions.get(positionName);
    }
}
