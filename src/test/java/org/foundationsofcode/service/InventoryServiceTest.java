package org.foundationsofcode.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryServiceTest {

    private List<String> inventory;
    private InventoryService inventoryService;

    @BeforeEach
    void setup() {
        Map<String, Double> catalog = new HashMap<>();
        catalog.put("MacBook Pro", 1299.99);
        catalog.put("Logitech Mouse", 49.99);
        catalog.put("Corsair RAM 64GB", 799.99);

        inventoryService = new InventoryService(catalog);
        inventory = new ArrayList<>();
    }

    @Test
    void addItem_addsNonBlankItem() {
        // Arrange
        String item = "Dell Monitor";
        // Act
        inventoryService.addItem(inventory, item);
        // Assert
        assertEquals(1, inventory.size());
        assertEquals("Dell Monitor", inventory.get(0));
    }

    @Test
    void addItem_ignoresNullOrBlank() {
        // Act
        inventoryService.addItem(inventory, null);
        // Assert
        assertEquals(0, inventory.size());
        assertTrue(inventory.isEmpty());
    }

    @Test
    void getPrice_returns0ForUnknownItem() {
        // Arrange
        String item = "DSLR Camera";
        // Act
        double price = inventoryService.getPrice(item);
        // Assert
        assertEquals(0.0, price);
    }

    @Test
    void calculateTotalValue_sumsCatalogPrices() {
        // Act
        inventoryService.addItem(inventory, "MacBook Pro");
        inventoryService.addItem(inventory, "Logitech Mouse");

        double totalValue = inventoryService.calculateTotalValue(inventory);

        // Assert
        assertEquals(1349.98, totalValue);
    }
}
