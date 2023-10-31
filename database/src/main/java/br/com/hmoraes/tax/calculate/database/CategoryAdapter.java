package br.com.hmoraes.tax.calculate.database;

import br.com.hmoraes.tax.calculate.database.entity.CategoryData;
import br.com.hmoraes.tax.calculate.database.mapper.CategoryEntityDataMapper;
import br.com.hmoraes.tax.calculate.database.repository.CategoryRepository;
import br.com.hmoraes.tax.calculate.port.database.CategoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryGateway {

    private final CategoryRepository repository;

    private final CategoryEntityDataMapper mapper;


    @Override
    public String findById(Long id) {
        Optional<CategoryData> data = this.repository.findById(id);

        return data.map(CategoryData::getName).orElse(null);
    }

    @Override
    public Long findByName(String name) {
        Optional<CategoryData> data = this.repository.findByName(name);

        return data.map(CategoryData::getId).orElse(null);
    }
}
