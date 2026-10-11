package com.example.shop;

/**
 * Works out what a customer pays for an item.
 * Prices are in paise (1 rupee = 100 paise) so there is no floating-point rounding.
 */
public final class Pricing {

	/** Extra discount for loyalty members, on top of any coupon (SHOP-42). */
	static final int MEMBER_BONUS_PERCENT = 5;

	private Pricing() {
	}

	/**
	 * @param pricePaise      list price, must be 0 or more
	 * @param discountPercent coupon discount, 0 to 100
	 * @param member          whether the customer is a loyalty member (gets an extra 5% off)
	 * @return the price after discount, never negative
	 */
	public static long finalPrice(long pricePaise, int discountPercent, boolean member) {
		if (pricePaise < 0) {
			throw new IllegalArgumentException("price must not be negative: " + pricePaise);
		}
		if (discountPercent < 0 || discountPercent > 100) {
			throw new IllegalArgumentException("discount must be 0..100: " + discountPercent);
		}
		int effectivePercent = member ? discountPercent + MEMBER_BONUS_PERCENT : discountPercent;
		long discount = pricePaise * effectivePercent / 100;
		return pricePaise - discount;
	}
}
