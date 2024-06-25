package com.example.dishorder.order.dto;

import lombok.Data;

@Data
public class RequestOrder {
    private Long tableId;
    private boolean isCompleted;
}
