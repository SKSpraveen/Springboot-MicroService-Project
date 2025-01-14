package com.example.Products.Service;

import com.example.Products.DTO.ProductDTO;
import com.example.Products.Entity.ProductEntity;
import com.example.Products.Repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO save(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, ProductEntity.class));
        return productDTO;
    }

    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntities = productRepo.findAll();
        return modelMapper.map(productEntities, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, ProductEntity.class));
        return productDTO;
    }

    public ProductDTO findProductById(Integer id) {
        ProductEntity product = productRepo.getProductById(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    public String deleteProduct(Integer id) {
        productRepo.deleteById(id);
        return "Product deleted";
    }


}
