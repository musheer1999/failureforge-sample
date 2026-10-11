package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class OrderTest {

	@Test
	void payThenRefund() {
		Order order = new Order(50_000);
		order.pay();
		assertEquals(50_000, order.refund());
		assertEquals(Order.Status.REFUNDED, order.status());
	}

	@Test
	void cannotRefundUnpaidOrder() {
		assertThrows(IllegalStateException.class, () -> new Order(50_000).refund());
	}

	@Test
	void cancelNewOrder() {
		Order order = new Order(50_000);
		order.cancel();
		assertEquals(Order.Status.CANCELLED, order.status());
	}
}
