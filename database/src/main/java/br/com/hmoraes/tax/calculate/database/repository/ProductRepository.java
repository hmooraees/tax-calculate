package br.com.hmoraes.tax.calculate.database.repository;

import br.com.hmoraes.tax.calculate.database.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductData, String> {

    Optional<ProductData> findByName(String name);
}
