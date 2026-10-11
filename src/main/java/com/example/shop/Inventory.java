package com.example.shop;

/** Stock of one product. Safe to use from many checkout threads at once. */
public final class Inventory {

	private int available;

	public Inventory(int available) {
		if (available < 0) {
			throw new IllegalArgumentException("stock must not be negative: " + available);
		}
		this.available = available;
	}

	/**
	 * Reserves {@code quantity} units if that many are available.
	 *
	 * @return true if reserved; false (and nothing changes) if there isn't enough stock
	 */
	public synchronized boolean reserve(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("quantity must be positive: " + quantity);
		}
		if (available < quantity) {
			return false;
		}
		available -= quantity;
		return true;
	}

	public synchronized int available() {
		return available;
	}
}
