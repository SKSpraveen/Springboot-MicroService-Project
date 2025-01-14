package com.example.Products.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntity {

    @Id
    private int id;
    private int productId;
    private String productName;
    private String productDescription;
    private int forSale;
}
