package br.com.hmoraes.tax.calculate.presenter.mapper;

import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductViewMapper {

    ProductView toView(ProductOutbound outbound);
}
