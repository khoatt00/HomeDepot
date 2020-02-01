package com.ujjwalkhanepani.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.exception.IncorrectOrderException;
import com.ujjwalkhanepani.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public List<OrderDTO> getOrders() {
		logger.info("Entering getOrders");
		List<OrderDTO> ordersDTOs = orderService.getOrders();
		return ordersDTOs;
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<?> saveOrders(@RequestBody OrderDTO orderDto) {
		orderService.saveOrder(orderDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.PATCH)
	public ResponseEntity<?> partialOrderUpdate(@PathVariable("orderId") String orderId, @RequestBody OrderDTO orderDto)
			throws IncorrectOrderException {
		orderService.updateOrder(orderId, orderDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity<?> fullOrderUpdate(@PathVariable("orderId") String orderId, @RequestBody OrderDTO orderDto)
			throws IncorrectOrderException {
		orderService.updateOrder(orderId, orderDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
