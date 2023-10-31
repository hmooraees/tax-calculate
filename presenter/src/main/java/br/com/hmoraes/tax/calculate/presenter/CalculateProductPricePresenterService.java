package br.com.hmoraes.tax.calculate.presenter;

import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductView;
import br.com.hmoraes.tax.calculate.port.presenter.CalculateProductPricePresenter;
import br.com.hmoraes.tax.calculate.presenter.mapper.ProductViewMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class CalculateProductPricePresenterService implements CalculateProductPricePresenter {

    private ProductView view;

    private final ProductViewMapper mapper;

    public void present(ProductOutbound outbound) {
        this.view = this.mapper.toView(outbound);
    }
}
