package org.foundationsofcode.service;

import java.util.List;
import java.util.Map;

public class InventoryService {

    private final Map<String, Double> priceCatalog;

    public InventoryService(Map<String, Double> priceCatalog) {
        this.priceCatalog = priceCatalog;
    }

    /* Adds an item to the inventory list */
    public void addItem(List<String> inventory, String item) {
        if (item != null && !item.trim().isEmpty()) {
            inventory.add(item);
        }
    }

    /* Retrieves the price of an item from the price catalog */
    public double getPrice(String item) {
        if (priceCatalog.containsKey(item))
            return priceCatalog.get(item);
        return 0.0;
    }

    /* Calculates the total value of all items in the inventory */
    public double calculateTotalValue(List<String> inventory) {
        double total = 0.0;
        total = logInventory(inventory, total);
        return total;
    }

      double logInventory(List<String> inventory, double total) {
        for (int i = 0; i < inventory.size(); i++) {
            total += getPrice(inventory.get(i));
        }
        return total;
    }

}
