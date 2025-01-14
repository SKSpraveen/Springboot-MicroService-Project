package com.example.Orders.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {

    @Id
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
