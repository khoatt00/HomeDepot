package com.ujjwalkhanepani.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ujjwalkhanepani.model.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}
