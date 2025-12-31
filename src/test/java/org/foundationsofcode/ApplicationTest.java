package org.foundationsofcode;

import org.foundationsofcode.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {

    private Map<String, Double> priceCatalog;
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        priceCatalog = new HashMap<>();
        inventoryService = new InventoryService(priceCatalog);
        setupCatalog();
    }

    @Test
    void inventoryStartsEmpty() {
        List<String> inventory = new ArrayList<>();
        assertTrue(inventory.isEmpty());
    }

    @Test
    void addingItemsIncreasesInventorySize() {
        List<String> inventory = new ArrayList<>();
        inventoryService.addItem(inventory, "MacBook Pro");
        inventoryService.addItem(inventory, "Logitech Mouse");

        assertEquals(2, inventory.size());
    }

    @Test
    void addedItemsAreRetrievable() {
        List<String> inventory = new ArrayList<>();
        inventoryService.addItem(inventory, "MacBook Pro");

        assertTrue(inventory.contains("MacBook Pro"));
    }

    @Test
    void priceRetrievalForKnownItem() {
        double price = inventoryService.getPrice("MacBook Pro");
        assertEquals(1299.99, price, 0.01);
    }

    @Test
    void priceRetrievalForUnknownItemReturnsZero() {
        double price = inventoryService.getPrice("Unknown Item");
        assertEquals(0.0, price);
    }

    @Test
    void calculateTotalValueWithEmptyInventory() {
        List<String> inventory = new ArrayList<>();
        double totalValue = inventoryService.calculateTotalValue(inventory);
        assertEquals(0.0, totalValue);
    }

    @Test
    void calculateTotalValueWithSingleItem() {
        List<String> inventory = new ArrayList<>();
        inventoryService.addItem(inventory, "Logitech Mouse");

        double totalValue = inventoryService.calculateTotalValue(inventory);
        assertEquals(49.99, totalValue, 0.01);
    }

    @Test
    void calculateTotalValueWithMultipleItems() {
        List<String> inventory = new ArrayList<>();
        inventoryService.addItem(inventory, "MacBook Pro");
        inventoryService.addItem(inventory, "Logitech Mouse");
        inventoryService.addItem(inventory, "Corsair RAM 64GB");

        double totalValue = inventoryService.calculateTotalValue(inventory);
        assertEquals(2149.97, totalValue, 0.01);
    }

    @Test
    void calculateTotalValueWithDuplicateItems() {
        List<String> inventory = new ArrayList<>();
        inventoryService.addItem(inventory, "Logitech Mouse");
        inventoryService.addItem(inventory, "Logitech Mouse");

        double totalValue = inventoryService.calculateTotalValue(inventory);
        assertEquals(99.98, totalValue, 0.01);
    }

    @Test
    void premiumItemThresholdAt100() {
        double premiumPrice = inventoryService.getPrice("MacBook Pro");
        assertTrue(premiumPrice > 100);
    }

    @Test
    void regularItemThresholdBelow100() {
        double regularPrice = inventoryService.getPrice("Logitech Mouse");
        assertTrue(regularPrice <= 100);
    }

    private void setupCatalog() {
        priceCatalog.put("MacBook Pro", 1299.99);
        priceCatalog.put("Logitech Mouse", 49.99);
        priceCatalog.put("Corsair RAM 64GB", 799.99);
        priceCatalog.put("Dell Monitor", 199.99);
    }
}