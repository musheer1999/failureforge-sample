package com.example.shop;

/**
 * An order's payment life cycle: NEW -> PAID -> REFUNDED, or NEW/PAID -> CANCELLED.
 * A paid order that is cancelled can still be refunded (SHOP-43). Amounts are in paise.
 */
public final class Order {

	public enum Status {
		NEW, PAID, CANCELLED, REFUNDED
	}

	private final long amountPaise;
	private Status status = Status.NEW;
	private boolean paid;
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
		paid = true;
	}

	public void cancel() {
		if (status == Status.REFUNDED || status == Status.CANCELLED) {
			throw new IllegalStateException("already " + status);
		}
		status = Status.CANCELLED;
	}

	/** Refunds the full amount of a paid order, also after it was cancelled. @return the amount refunded now */
	public long refund() {
		boolean refundable = status == Status.PAID || (status == Status.CANCELLED && paid);
		if (!refundable) {
			throw new IllegalStateException("can only refund a paid order, not " + status);
		}
		if (status == Status.PAID) {
			status = Status.REFUNDED;
		}
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
