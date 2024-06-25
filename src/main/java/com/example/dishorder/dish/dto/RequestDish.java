package com.example.dishorder.dish.dto;

import lombok.Data;

@Data
public class RequestDish {
    private String name;
    private String description;
    private double price;
    private Long orderId;
}
