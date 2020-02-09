package com.ujjwalkhanepani.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ujjwalkhanepani.utilites.DateConverter;
import lombok.Data;

import java.util.Date;

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
    @JsonDeserialize(using = DateConverter.class)
    private Date tripDate;


}
