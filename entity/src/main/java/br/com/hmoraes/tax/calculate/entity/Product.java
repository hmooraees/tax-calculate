package br.com.hmoraes.tax.calculate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;

    private String name;

    private String category;

    private Long categoryId;

    private Double basePrice;

    private Double taxPrice;
}
