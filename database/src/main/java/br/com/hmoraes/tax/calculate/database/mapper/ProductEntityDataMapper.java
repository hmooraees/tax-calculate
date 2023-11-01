package br.com.hmoraes.tax.calculate.database.mapper;

import br.com.hmoraes.tax.calculate.database.entity.ProductData;
import br.com.hmoraes.tax.calculate.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityDataMapper {

    ProductData toData(Product entity);

    Product toEntity(ProductData data);
}
