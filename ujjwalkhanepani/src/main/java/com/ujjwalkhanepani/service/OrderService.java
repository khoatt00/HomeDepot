package com.ujjwalkhanepani.service;

import java.util.List;

import com.ujjwalkhanepani.translator.OrderTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.exceptions.IncorrectOrderException;
import com.ujjwalkhanepani.model.Order;
import com.ujjwalkhanepani.repository.OrderRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private PaymentService paymentService;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }


    @Value("${orders.per.page}")
    private String ordersPerPage;

    public List<OrderDTO> getOrders(Integer page) {
        Pageable pageable = PageRequest.of(page, Integer.parseInt(ordersPerPage));
        List<Order> orders = orderRepository.findAll(pageable).getContent();
        return new OrderTranslator().mapOrders(orders);
    }

    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return new OrderTranslator().mapOrders(orders);
    }

    public OrderDTO saveOrder(OrderDTO orderDto) {
        Order order = new Order();
        OrderTranslator.transformToEntity(orderDto, order);
        order = orderRepository.save(order);
        paymentService.savePayment(orderDto, order);
        OrderTranslator.transformToDTO(order, orderDto);
        return orderDto;
    }

    public OrderDTO updateOrder(String orderId, OrderDTO orderDTO) throws IncorrectOrderException {
        Order order = orderRepository.findById(Long.parseLong(orderId))
                .orElseThrow(() -> new IncorrectOrderException("Order with " + orderId + " doesn't exist"));
        OrderTranslator.transformToEntity(orderDTO, order);
        order = orderRepository.save(order);
        OrderTranslator.transformToDTO(order, orderDTO);
        return orderDTO;
    }

}
