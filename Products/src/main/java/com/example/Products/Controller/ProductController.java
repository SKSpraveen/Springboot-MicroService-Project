package com.example.Products.Controller;

import com.example.Products.DTO.ProductDTO;
import com.example.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping("/getAll")
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    @PutMapping("/update")
    public ProductDTO update(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/get/{id}")
    public ProductDTO getProductById(@PathVariable Integer id) {
        return productService.findProductById(id);
    }
}
