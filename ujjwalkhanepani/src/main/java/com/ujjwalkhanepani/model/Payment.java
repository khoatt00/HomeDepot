package com.ujjwalkhanepani.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "payment_details")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private Integer amountPaid;


}
