package com.ujjwalkhanepani.service;

import com.ujjwalkhanepani.dto.OrderDTO;
import com.ujjwalkhanepani.model.Order;
import com.ujjwalkhanepani.model.Payment;
import com.ujjwalkhanepani.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Saves payment based on order DTO
     *
     * @param orderDTO orderDto received as a request
     * @param order    order being saved in database
     */
    public void savePayment(OrderDTO orderDTO, Order order) {
        Payment payment = new Payment();
        Integer amountPaid = orderDTO.getAmountPaid();
        if(orderDTO.getFullPaid()) {
            amountPaid = orderDTO.getTripAmount();
        }
        payment.setAmountPaid(amountPaid);
        payment.setOrder(order);
        paymentRepository.save(payment);
    }

}
