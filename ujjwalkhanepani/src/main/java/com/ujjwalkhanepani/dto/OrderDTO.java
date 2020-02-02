package com.ujjwalkhanepani.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private long id;
    private String name;
    private String location;
    private String phoneNumber;
    private Integer tripAmount;
    private Integer amountPaid;
    private Integer remainingAmount;
    private Boolean fullPaid;


}
