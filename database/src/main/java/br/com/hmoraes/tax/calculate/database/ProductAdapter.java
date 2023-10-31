package br.com.hmoraes.tax.calculate.database;

import br.com.hmoraes.tax.calculate.database.entity.ProductData;
import br.com.hmoraes.tax.calculate.database.mapper.ProductEntityDataMapper;
import br.com.hmoraes.tax.calculate.database.repository.ProductRepository;
import br.com.hmoraes.tax.calculate.entity.Product;
import br.com.hmoraes.tax.calculate.port.database.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductGateway {

    private final ProductRepository repository;

    private final ProductEntityDataMapper mapper;

    @Override
    public Optional<Product> findByName(String name) {
        return this.repository.findByName(name).map(e -> {

            return Product.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .categoryId(e.getCategoryId())
                    .basePrice(e.getBasePrice())
                    .taxPrice(e.getTaxPrice())
                    .build();
        });
    }

    @Override
    public Product save(Product product) {
        ProductData data = this.mapper.toData(product);

        ProductData response = this.repository.save(data);

        return this.mapper.toEntity(response);
    }
}
