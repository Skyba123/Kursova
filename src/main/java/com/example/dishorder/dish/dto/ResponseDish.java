package com.example.dishorder.dish.dto;

import com.example.dishorder.dish.entity.DishEntity;
import com.example.dishorder.order.dto.ResponseOrder;
import lombok.Data;

@Data
public class ResponseDish {
    private Long id;
    private String name;
    private String description;
    private double price;
    private ResponseOrder order;

    public static ResponseDish toModel(DishEntity dishEntity) {
        ResponseDish response = new ResponseDish();
        response.setId(dishEntity.getId());
        response.setName(dishEntity.getName());
        response.setDescription(dishEntity.getDescription());
        response.setPrice(dishEntity.getPrice());
        if(dishEntity.getOrder() != null) {
            response.setOrder(ResponseOrder.toModel(dishEntity.getOrder()));
        }
        return response;
    }
}
