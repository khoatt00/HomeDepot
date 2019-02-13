package com.ujjwalkhanepani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.model.Order;
import com.ujjwalkhanepani.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public List<Order> getOrders() {
		return orderService.getOrders();
	}

	@RequestMapping(value = "/order" , method = RequestMethod.POST) 
	public ResponseEntity saveOrders(@RequestBody OrderDTO order) {
		orderService.saveOrder(order);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
