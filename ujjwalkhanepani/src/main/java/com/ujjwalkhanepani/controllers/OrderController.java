package com.ujjwalkhanepani.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.exceptions.IncorrectOrderException;
import com.ujjwalkhanepani.service.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<OrderDTO> getOrders() {
        log.info("Entering getOrders");
        return orderService.getOrders();
    }


    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<?> saveOrders(@RequestBody
                                        @Valid OrderDTO orderDto) {
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

    @DeleteMapping(value = "/order/{orderId}")
    public ResponseEntity<?> delete(@PathVariable("orderId") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
