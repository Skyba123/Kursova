package com.example.dishorder.table.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestTable {
    private int number;
    private int seats;
    private boolean isOccupied;
}
