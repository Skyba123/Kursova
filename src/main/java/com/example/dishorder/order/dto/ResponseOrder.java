package com.example.dishorder.order.dto;

import com.example.dishorder.order.entity.OrderEntity;
import com.example.dishorder.table.dto.ResponseTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    private Long id;
    private ResponseTable table;
    private boolean isCompleted;
    private Date createdAt;

    public static ResponseOrder toModel(OrderEntity entity) {
        ResponseOrder model = new ResponseOrder();
        model.setId(entity.getId());
        model.setCompleted(entity.isCompleted());
        model.setCreatedAt(entity.getCreatedAt());
        if(entity.getTable() != null) {
            model.setTable(ResponseTable.toModel(entity.getTable()));
        }
        return model;
    }
}
