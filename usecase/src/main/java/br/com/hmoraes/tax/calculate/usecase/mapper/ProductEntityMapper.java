package br.com.hmoraes.tax.calculate.usecase.mapper;

import br.com.hmoraes.tax.calculate.entity.Product;
import br.com.hmoraes.tax.calculate.port.dto.ProductInbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    Product toEntity(ProductInbound inbound);

    ProductOutbound toOutbound(Product entity);
}
