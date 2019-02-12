package com.ujjwalkhanepani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ujjwalkhanepani.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
