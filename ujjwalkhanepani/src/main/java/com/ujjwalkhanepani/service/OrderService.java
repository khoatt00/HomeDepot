package com.ujjwalkhanepani.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.model.Order;
import com.ujjwalkhanepani.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getOrders() {
		return (List<Order>) orderRepository.findAll();
	}

	public void saveOrder(OrderDTO orderDto) {
		Order order = new Order();
		order.setName(orderDto.getName());
		orderRepository.save(order);
		
	}
}
