package com.sasmithx.hateoas.assembler;

import com.sasmithx.hateoas.controller.ProductController;
import com.sasmithx.hateoas.dto.ProductResponse;
import com.sasmithx.hateoas.entity.Product;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, ProductResponse> {
    @Override
    public ProductResponse toModel(Product entity) {

        ProductResponse model = new ProductResponse();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());

        model.add(linkTo(methodOn(ProductController.class)
                .getById(entity.getId()))
                .withSelfRel());

        model.add(linkTo(methodOn(ProductController.class)
                .update(entity.getId(), null))
                .withRel("update"));

        model.add(linkTo(methodOn(ProductController.class)
                .delete(entity.getId()))
                .withRel("delete"));

        return model;
    }

}
