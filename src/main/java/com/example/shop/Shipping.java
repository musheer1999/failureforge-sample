package com.example.shop;

/** Shipping cost (SHOP-46): free from 50,000 paise (Rs 500), otherwise 4,000 paise (Rs 40). */
public final class Shipping {

	static final long FREE_FROM_PAISE = 50_000;
	static final long STANDARD_PAISE = 4_000;

	private Shipping() {
	}

	/**
	 * @param orderTotalPaise total of the items, 0 or more
	 * @return the shipping cost in paise, never negative
	 */
	public static long cost(long orderTotalPaise) {
		if (orderTotalPaise < 0) {
			throw new IllegalArgumentException("order total must not be negative: " + orderTotalPaise);
		}
		return orderTotalPaise >= FREE_FROM_PAISE ? 0 : STANDARD_PAISE;
	}
}
