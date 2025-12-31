package org.foundationsofcode;

import org.foundationsofcode.service.InventoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {

    private static final Map<String, Double> priceCatalog = new HashMap<>();
    private static final InventoryService inventoryService = new InventoryService(priceCatalog);

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Application.class.getName());
        logger.info("Hello and welcome!");

        setupCatalog();

        // 1. Variables & Initialization
        List<String> inventory = new ArrayList<>();

        // 2. Adding items (Methods & Collections)
        inventoryService.addItem(inventory, "MacBook Pro");
        inventoryService.addItem(inventory, "Logitech Mouse");
        inventoryService.addItem(inventory, "Corsair RAM 64GB");

        // 3. Loops (Traditional for-each)
        logger.info("Current Inventory:");
        for (String item: inventory) {
            double price = inventoryService.getPrice(item);

            if (price > 100) {
                logger.log(Level.INFO, "Premium Item: {0} - Price: ${1}", new Object[]{item, price});
            } else {
                logger.log(Level.INFO, "Regular Item: {0} - Price: ${1}", new Object[]{item, price});
            }
        }

        // 5. Logic demonstration: Calculate total value
        double totalValue = inventoryService.calculateTotalValue(inventory);
        logger.log(Level.INFO, "Total Inventory Value: ${0}", totalValue);

    }

    /* Sets up the initial price catalog with some items and their prices */
    private static void setupCatalog() {
        priceCatalog.put("MacBook Pro", 1299.99);
        priceCatalog.put("Logitech Mouse", 49.99);
        priceCatalog.put("Corsair RAM 64GB", 799.99);
        priceCatalog.put("Dell Monitor", 199.99);
    }
}
