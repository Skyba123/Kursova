package com.example.dishorder.table.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "app_table")
@NoArgsConstructor
@AllArgsConstructor
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private int seats;
    private boolean isOccupied;

    public TableEntity(int number, int seats, boolean isOccupied) {
        this.number = number;
        this.seats = seats;
        this.isOccupied = isOccupied;
    }
}
