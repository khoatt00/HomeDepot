package com.ujjwalkhanepani.translator;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.model.Order;
import com.ujjwalkhanepani.model.Payment;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderTranslator {

    public static void transformToDTO(Order order, OrderDTO orderDTO) {
        BeanUtils.copyProperties(order, orderDTO);
    }

    public static void transformToEntity(OrderDTO orderDTO, Order order) {
        BeanUtils.copyProperties(orderDTO, order);
        if (orderDTO.getFullPaid()) {
            order.setRemainingAmount(0);
            return;
        }
        order.setRemainingAmount(orderDTO.getTripAmount() - orderDTO.getAmountPaid());
    }

    public List<OrderDTO> mapOrders(List<Order> orders) {
        return orders.stream().map(order -> {
            OrderDTO orderDTO = new OrderDTO();
            transformToDTO(order, orderDTO);
            List<Payment> payments = order.getPayments();
            Integer paymentsMade = payments.stream().map(Payment::getAmountPaid).mapToInt(Integer::intValue).sum();
            orderDTO.setAmountPaid(paymentsMade);
            return orderDTO;
        }).collect(Collectors.toList());
    }
}
