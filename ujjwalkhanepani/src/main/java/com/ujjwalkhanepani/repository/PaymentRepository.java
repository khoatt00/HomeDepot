package com.ujjwalkhanepani.repository;

import com.ujjwalkhanepani.model.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {
}
