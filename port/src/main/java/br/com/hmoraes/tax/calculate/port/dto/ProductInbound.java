package br.com.hmoraes.tax.calculate.port.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInbound {

    private String name;
    private String category;
    private Double basePrice;
}
