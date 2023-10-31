package br.com.hmoraes.tax.calculate.port.usecase;

import br.com.hmoraes.tax.calculate.port.dto.ProductInbound;

public interface CalculateProductPriceUseCase {

    void execute(ProductInbound inbound);
}
