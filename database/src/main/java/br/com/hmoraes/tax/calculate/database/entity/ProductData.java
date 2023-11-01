package br.com.hmoraes.tax.calculate.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductData {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Column(name = "BASE_PRICE", nullable = false)
    private Double basePrice;

    @Column(name = "TAX_PRICE", nullable = false)
    private Double taxPrice;
}
