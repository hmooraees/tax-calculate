package br.com.hmoraes.tax.calculate.port.database;

import br.com.hmoraes.tax.calculate.entity.Product;

import java.util.Optional;

public interface ProductGateway {

    Optional<Product> findByName(String name);

    Product save(Product product);
}
