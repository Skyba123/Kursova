package com.example.dishorder.customer.dto;

import com.example.dishorder.customer.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCustomer {
    private Long id;
    private String name;
    private String phone;

    public static ResponseCustomer toModel(CustomerEntity entity) {
        ResponseCustomer model = new ResponseCustomer();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPhone(entity.getPhone());
        return model;
    }
}
