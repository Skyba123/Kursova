package com.example.dishorder.table.dto;

import com.example.dishorder.table.entity.TableEntity;
import lombok.Data;

@Data
public class ResponseTable {
    private Long id;
    private int number;
    private int seats;
    private boolean isOccupied;

    public static ResponseTable toModel(TableEntity entity) {
        ResponseTable model = new ResponseTable();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setSeats(entity.getSeats());
        model.setOccupied(entity.isOccupied());
        return model;
    }
}
