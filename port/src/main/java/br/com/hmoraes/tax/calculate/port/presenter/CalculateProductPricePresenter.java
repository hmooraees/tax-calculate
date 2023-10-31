package br.com.hmoraes.tax.calculate.port.presenter;

import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductView;

public interface CalculateProductPricePresenter {

    ProductView getView();

    void present(ProductOutbound outbound);
}
