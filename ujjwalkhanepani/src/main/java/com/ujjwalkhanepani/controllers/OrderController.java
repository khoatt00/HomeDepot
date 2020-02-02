package com.ujjwalkhanepani.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.exceptions.IncorrectOrderException;
import com.ujjwalkhanepani.service.OrderService;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<OrderDTO> getOrders(@RequestParam(value = "page", required = false, defaultValue = "0") String page ) {
        log.info("Entering getOrders");
        return orderService.getOrders(Integer.parseInt(page) - 1);
    }


    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<?> saveOrders(@RequestBody OrderDTO orderDto) {
        OrderDTO orderDTO = orderService.saveOrder(orderDto);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> partialOrderUpdate(@PathVariable("orderId") String orderId, @RequestBody OrderDTO orderDto)
            throws IncorrectOrderException {
        OrderDTO orderDTO = orderService.updateOrder(orderId, orderDto);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<?> fullOrderUpdate(@PathVariable("orderId") String orderId, @RequestBody OrderDTO orderDto)
            throws IncorrectOrderException {
        orderService.updateOrder(orderId, orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
