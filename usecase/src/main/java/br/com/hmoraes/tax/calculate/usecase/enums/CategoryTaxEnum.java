package br.com.hmoraes.tax.calculate.usecase.enums;

import lombok.Getter;

@Getter
public enum CategoryTaxEnum {

    VIDA(1L, 0.01, 0.02, 0.03),
    AUTO(2L, 0.055, 0.04, 0.01),
    VIAGEM(3L, 0.02, 0.04, 0.01),
    RESIDENCIAL(4L, 0.04, 0.025, 0.03),
    PATRIMONIAL(5L, 0.05, 0.03, 0.02);

    private Long id;

    private Double iof;

    private Double pis;

    private Double cofins;

    CategoryTaxEnum(Long id, Double iof, Double pis, Double cofins) {
        this.id = id;
        this.iof = iof;
        this.pis = pis;
        this.cofins = cofins;
    }
}
