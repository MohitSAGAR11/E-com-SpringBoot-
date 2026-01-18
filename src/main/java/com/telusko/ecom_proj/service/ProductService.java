package com.telusko.ecom_proj.service;

import com.telusko.ecom_proj.dtos.ProductRequest;
import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(ProductRequest productRequest, MultipartFile imageFile) throws IOException {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setBrand(productRequest.brand());
        product.setDescription(productRequest.description());
        product.setPrice(Long.parseLong(productRequest.price()));
        product.setCategory(productRequest.category());
        product.setQuantity(Integer.parseInt(productRequest.quantity()));
        product.setReleaseDate(Date.valueOf(productRequest.releaseDate()));
        product.setAvailable(productRequest.available());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }
}
