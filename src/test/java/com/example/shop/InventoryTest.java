package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InventoryTest {

	@Test
	void reservesWhenEnoughStock() {
		Inventory stock = new Inventory(10);
		assertTrue(stock.reserve(3));
		assertEquals(7, stock.available());
	}

	@Test
	void refusesWhenNotEnoughStock() {
		Inventory stock = new Inventory(2);
		assertFalse(stock.reserve(3));
		assertEquals(2, stock.available());
	}
}
