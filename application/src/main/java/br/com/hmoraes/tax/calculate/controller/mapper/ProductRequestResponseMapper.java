package br.com.hmoraes.tax.calculate.controller.mapper;

import br.com.hmoraes.tax.calculate.controller.dto.ProductRequest;
import br.com.hmoraes.tax.calculate.controller.dto.ProductResponse;
import br.com.hmoraes.tax.calculate.port.dto.ProductInbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRequestResponseMapper {

    ProductInbound toInBound(ProductRequest request);

    ProductResponse toResponse(ProductView view);
}
