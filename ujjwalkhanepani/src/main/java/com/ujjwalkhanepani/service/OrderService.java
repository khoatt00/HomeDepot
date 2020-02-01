package com.ujjwalkhanepani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.exception.IncorrectOrderException;
import com.ujjwalkhanepani.model.Order;
import com.ujjwalkhanepani.model.Payment;
import com.ujjwalkhanepani.repository.OrderRepository;
import com.ujjwalkhanepani.repository.PaymentRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Value("${orders.per.page}")
	private String ordersPerPage;

	public List<OrderDTO> getOrders() {
		Pageable pageable = PageRequest.of(0, Integer.parseInt(ordersPerPage));
		List<OrderDTO> orderDTOs = new ArrayList<>();
		List<Order> orders = orderRepository.findAll(pageable).getContent();
		orderDTOs = orders.stream().map(order -> {
			OrderDTO orderDTO = new OrderDTO();
			if (order.getName() != null) {
				orderDTO.setName(order.getName());
			}
			if (order.getLocation() != null) {
				orderDTO.setLocation(order.getLocation());
			}
			if (order.getPhoneNumber() != null) {
				orderDTO.setPhoneNumber(order.getPhoneNumber());
			}
			if (order.getPaymentStatus() != null) {
				orderDTO.setPaymentStatus(order.getPaymentStatus());
			}
			if (order.getOrderStatus() != null) {
				orderDTO.setOrderStatus(order.getOrderStatus());
			}
			Long totalAmount = order.getPayments().stream().mapToLong(payment -> payment.getAmount().longValue()).sum();
			orderDTO.setAmount(totalAmount);
			return orderDTO;
		}).collect(Collectors.toList());
		return orderDTOs;
	}

	public void transform() {
		
	}
	
	public void saveOrder(OrderDTO orderDto) {
		Order order = new Order();
		order = orderRepository.save(transformFromOrderDTO(orderDto, order));
		paymentRepository.save(transformToPaymentFromDTO(order, orderDto));
	}

	private Order transformFromOrderDTO(OrderDTO orderDTO, Order order) {
		if (orderDTO.getName() != null) {
			order.setName(orderDTO.getName());
		}
		if (orderDTO.getLocation() != null) {
			order.setLocation(orderDTO.getLocation());
		}
		if (orderDTO.getPhoneNumber() != null) {
			order.setPhoneNumber(orderDTO.getPhoneNumber());
		}
		if (orderDTO.getPaymentStatus() != null) {
			order.setPaymentStatus(orderDTO.getPaymentStatus());
		}
		if (orderDTO.getOrderStatus() != null) {
			order.setOrderStatus(orderDTO.getOrderStatus());
		}
		return order;
	}

	private Payment transformToPaymentFromDTO(Order order, OrderDTO orderDTO) {
		Payment payment = new Payment();
		payment.setAmount(orderDTO.getAmount());
		payment.setOrder(order);
		return payment;
	}

	public void updateOrder(String orderId, OrderDTO orderDTO) throws IncorrectOrderException {
		Order order = orderRepository.findById(Long.parseLong(orderId)).get();
		orderRepository.save(transformFromOrderDTO(orderDTO, order));
	}

}
