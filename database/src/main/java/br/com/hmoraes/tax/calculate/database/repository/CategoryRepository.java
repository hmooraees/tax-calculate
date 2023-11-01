package br.com.hmoraes.tax.calculate.database.repository;

import br.com.hmoraes.tax.calculate.database.entity.CategoryData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryData, Long> {

    Optional<CategoryData> findByName(String name);
}
