package com.sasmithx.hateoas.service;

import com.sasmithx.hateoas.dto.ProductRequest;
import com.sasmithx.hateoas.entity.Product;
import com.sasmithx.hateoas.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product create(ProductRequest request) {
        return repository.save(
                Product.builder()
                        .name(request.name())
                        .price(request.price())
                        .build()
        );
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product update(Long id, ProductRequest request) {
        Product product = getById(id);
        product.setName(request.name());
        product.setPrice(request.price());
        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
