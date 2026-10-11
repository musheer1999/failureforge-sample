package com.example.shop;

/**
 * An order's payment life cycle: NEW -> PAID -> REFUNDED, or NEW/PAID -> CANCELLED.
 * Amounts are in paise.
 */
public final class Order {

	public enum Status {
		NEW, PAID, CANCELLED, REFUNDED
	}

	private final long amountPaise;
	private Status status = Status.NEW;
	private long refundedPaise;

	public Order(long amountPaise) {
		if (amountPaise <= 0) {
			throw new IllegalArgumentException("amount must be positive: " + amountPaise);
		}
		this.amountPaise = amountPaise;
	}

	public void pay() {
		if (status != Status.NEW) {
			throw new IllegalStateException("can only pay a new order, not " + status);
		}
		status = Status.PAID;
	}

	public void cancel() {
		if (status == Status.REFUNDED || status == Status.CANCELLED) {
			throw new IllegalStateException("already " + status);
		}
		status = Status.CANCELLED;
	}

	/** Refunds the full amount of a paid order. @return the amount refunded now */
	public long refund() {
		if (status != Status.PAID) {
			throw new IllegalStateException("can only refund a paid order, not " + status);
		}
		status = Status.REFUNDED;
		refundedPaise += amountPaise;
		return amountPaise;
	}

	public Status status() {
		return status;
	}

	public long amountPaise() {
		return amountPaise;
	}

	/** Total refunded so far; never more than the amount paid. */
	public long refundedPaise() {
		return refundedPaise;
	}
}
