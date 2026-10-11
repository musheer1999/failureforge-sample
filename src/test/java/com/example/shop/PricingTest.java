package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PricingTest {

	@Test
	void noDiscount() {
		assertEquals(50_000, Pricing.finalPrice(50_000, 0, false));
	}

	@Test
	void tenPercentOff() {
		assertEquals(45_000, Pricing.finalPrice(50_000, 10, false));
	}

	@Test
	void twentyPercentOff() {
		assertEquals(40_000, Pricing.finalPrice(50_000, 20, false));
	}

	@Test
	void rejectsNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> Pricing.finalPrice(-1, 10, false));
	}

	@Test
	void rejectsDiscountOverHundred() {
		assertThrows(IllegalArgumentException.class, () -> Pricing.finalPrice(50_000, 150, false));
	}
}
