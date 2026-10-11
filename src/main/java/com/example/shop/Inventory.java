package com.example.shop;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/** Stock of one product. Safe to use from many checkout threads at once, without a global lock (SHOP-44). */
public final class Inventory {

	private final AtomicInteger available;
	/** Every successful reservation, for the analytics team (SHOP-44). */
	private final List<Integer> reservations = new CopyOnWriteArrayList<>();

	public Inventory(int available) {
		if (available < 0) {
			throw new IllegalArgumentException("stock must not be negative: " + available);
		}
		this.available = new AtomicInteger(available);
	}

	/**
	 * Reserves {@code quantity} units if that many are available.
	 *
	 * @return true if reserved; false (and nothing changes) if there isn't enough stock
	 */
	public boolean reserve(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("quantity must be positive: " + quantity);
		}
		int current = available.get();
		if (current < quantity) {
			return false;
		}
		reservations.add(quantity);
		available.set(current - quantity);
		return true;
	}

	public int available() {
		return available.get();
	}

	public List<Integer> reservations() {
		return List.copyOf(reservations);
	}
}
