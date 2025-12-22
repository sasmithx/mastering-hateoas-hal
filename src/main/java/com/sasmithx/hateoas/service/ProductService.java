package com.sasmithx.hateoas.service;

import com.sasmithx.hateoas.dto.ProductRequest;
import com.sasmithx.hateoas.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest request);
    Product getById(Long id);
    List<Product> getAll();
    Product update(Long id, ProductRequest request);
    void delete(Long id);
}
