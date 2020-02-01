package com.ujjwalkhanepani.dto;

import com.ujjwalkhanepani.model.enums.OrderStatus;
import com.ujjwalkhanepani.model.enums.PaymentStatus;

public class OrderDTO {

	private String name;
	private String location;
	private String phoneNumber;
	private OrderStatus orderStatus;
	private PaymentStatus paymentStatus;
	private Long amount;

	public String getName() {
		return name;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
