package com.example.shop;

/**
 * Works out what a customer pays for an item.
 * Prices are in paise (1 rupee = 100 paise) so there is no floating-point rounding.
 */
public final class Pricing {

	private Pricing() {
	}

	/**
	 * @param pricePaise      list price, must be 0 or more
	 * @param discountPercent coupon discount, 0 to 100
	 * @param member          whether the customer is a loyalty member (no member benefit yet)
	 * @return the price after discount, never negative
	 */
	public static long priceAfterDiscount(long pricePaise, int discountPercent, boolean member) {
		if (pricePaise < 0) {
			throw new IllegalArgumentException("price must not be negative: " + pricePaise);
		}
		if (discountPercent < 0 || discountPercent > 100) {
			throw new IllegalArgumentException("discount must be 0..100: " + discountPercent);
		}
		long discount = pricePaise * discountPercent / 100;
		return pricePaise - discount;
	}
}
