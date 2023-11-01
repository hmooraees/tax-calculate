package br.com.hmoraes.tax.calculate.usecase.mapper;

import br.com.hmoraes.tax.calculate.entity.Product;
import br.com.hmoraes.tax.calculate.port.dto.ProductInbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-31T22:33:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class ProductEntityMapperImpl implements ProductEntityMapper {

    @Override
    public Product toEntity(ProductInbound inbound) {
        if ( inbound == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( inbound.getName() );
        product.category( inbound.getCategory() );
        product.basePrice( inbound.getBasePrice() );

        return product.build();
    }

    @Override
    public ProductOutbound toOutbound(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductOutbound.ProductOutboundBuilder productOutbound = ProductOutbound.builder();

        productOutbound.id( entity.getId() );
        productOutbound.name( entity.getName() );
        productOutbound.category( entity.getCategory() );
        productOutbound.basePrice( entity.getBasePrice() );
        productOutbound.taxPrice( entity.getTaxPrice() );

        return productOutbound.build();
    }
}
