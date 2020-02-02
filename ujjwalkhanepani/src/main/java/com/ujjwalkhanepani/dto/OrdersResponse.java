package com.ujjwalkhanepani.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdersResponse {
    private Integer numberOfRows;
    private List<OrderDTO> orderDToList;
}
