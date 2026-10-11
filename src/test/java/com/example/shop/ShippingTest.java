package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShippingTest {

	@Test
	void freeFromFiveHundredRupees() {
		assertEquals(0, Shipping.cost(50_000));
	}

	@Test
	void smallOrdersPayForty() {
		assertEquals(4_000, Shipping.cost(10_000));
	}
}
