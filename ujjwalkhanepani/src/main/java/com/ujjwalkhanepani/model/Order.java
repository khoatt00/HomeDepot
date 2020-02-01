package com.ujjwalkhanepani.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ujjwalkhanepani.model.enums.OrderStatus;
import com.ujjwalkhanepani.model.enums.PaymentStatus;

@Entity
@Table(name = "order_details")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "phoneNumber", length = 10)
	private String phoneNumber;

	@Column(name = "location")
	private String location;

	@Enumerated(EnumType.STRING)
	@Column(name = "orderStatus")
	private OrderStatus orderStatus = OrderStatus.ORDER;

	@Enumerated(EnumType.STRING)
	@Column(name = "paymentStatus")
	private PaymentStatus paymentStatus = PaymentStatus.PENDING;

	@OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
	private List<Payment> payments = new ArrayList<>();

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	

}
