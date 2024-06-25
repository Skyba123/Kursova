package com.example.dishorder.order.entity;

import com.example.dishorder.dish.entity.DishEntity;
import com.example.dishorder.table.entity.TableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "app_order")
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<DishEntity> dishes;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    private boolean isCompleted;
    private Date createdAt;

    public OrderEntity(Long id, boolean completed, Date createdAt, TableEntity table) {
        this.id = id;
        this.isCompleted = completed;
        this.createdAt = createdAt;
        this.table = table;
    }
}
