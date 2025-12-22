package com.sasmithx.hateoas.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse extends RepresentationModel<ProductResponse> {
    private Long id;
    private String name;
    private double price;
}
