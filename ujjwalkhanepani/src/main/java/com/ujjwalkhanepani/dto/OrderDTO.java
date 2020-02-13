package com.ujjwalkhanepani.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ujjwalkhanepani.utilites.DateConverter;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderDTO {

    private long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String location;
    @NotNull
    @NotEmpty
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @NotNull
    @NotEmpty
    private String phoneNumber;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer tripAmount;
    private Integer amountPaid;
    private Integer remainingAmount;
    private Boolean fullPaid;
    @JsonDeserialize(using = DateConverter.class)
    private Date tripDate;


}
