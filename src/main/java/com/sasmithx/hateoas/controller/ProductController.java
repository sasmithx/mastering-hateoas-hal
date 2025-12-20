package com.sasmithx.hateoas.controller;

import com.sasmithx.hateoas.assembler.ProductModelAssembler;
import com.sasmithx.hateoas.dto.ProductRequest;
import com.sasmithx.hateoas.dto.ProductResponse;
import com.sasmithx.hateoas.entity.Product;
import com.sasmithx.hateoas.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductModelAssembler assembler;

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request) {

        Product product = service.create(request);
        return ResponseEntity
                .created(linkTo(methodOn(ProductController.class)
                        .getById(product.getId())).toUri())
                .body(assembler.toModel(product));
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return assembler.toModel(service.getById(id));
    }

    @GetMapping
    public CollectionModel<ProductResponse> getAll() {

        List<ProductResponse> products = service.getAll()
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(products,
                linkTo(methodOn(ProductController.class)
                        .getAll()).withSelfRel());
    }

    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return assembler.toModel(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
