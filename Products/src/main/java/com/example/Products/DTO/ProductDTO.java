package com.example.Products.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private int productId;
    private String productName;
    private String productDescription;
    private int forSale;

}
