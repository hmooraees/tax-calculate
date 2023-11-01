package br.com.hmoraes.tax.calculate.database;

import br.com.hmoraes.tax.calculate.database.entity.ProductData;
import br.com.hmoraes.tax.calculate.database.mapper.ProductEntityDataMapper;
import br.com.hmoraes.tax.calculate.database.repository.ProductRepository;
import br.com.hmoraes.tax.calculate.entity.Product;
import br.com.hmoraes.tax.calculate.port.database.ProductGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductGateway {

    private final ProductRepository repository;

    private final ProductEntityDataMapper mapper;

    @Override
    public Optional<Product> findByName(String name) {
        log.info("class=ProductAdapter method=findByName step=start name={}", name);

        Optional<Product> product = this.repository.findByName(name).map(e -> {

            return Product.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .categoryId(e.getCategoryId())
                    .basePrice(e.getBasePrice())
                    .taxPrice(e.getTaxPrice())
                    .build();
        });

        log.info("class=ProductAdapter method=findByName step=end name={}", name);

        return product;
    }

    @Override
    public Product save(Product product) {
        log.info("class=ProductAdapter method=save step=start object={}", product);

        ProductData data = this.mapper.toData(product);

        ProductData response = this.repository.save(data);

        log.info("class=ProductAdapter method=save step=end object={}", response);

        return this.mapper.toEntity(response);
    }
}
