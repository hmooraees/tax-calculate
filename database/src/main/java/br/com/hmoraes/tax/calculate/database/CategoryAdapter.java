package br.com.hmoraes.tax.calculate.database;

import br.com.hmoraes.tax.calculate.database.entity.CategoryData;
import br.com.hmoraes.tax.calculate.database.mapper.CategoryEntityDataMapper;
import br.com.hmoraes.tax.calculate.database.repository.CategoryRepository;
import br.com.hmoraes.tax.calculate.port.database.CategoryGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryGateway {

    private final CategoryRepository repository;

    private final CategoryEntityDataMapper mapper;


    @Override
    public String findById(Long id) {
        log.info("class=CategoryAdapter method=findById step=start id={}", id);

        Optional<CategoryData> data = this.repository.findById(id);

        log.info("class=CategoryAdapter method=findById step=end object={}", data);

        return data.map(CategoryData::getName).orElse(null);
    }

    @Override
    public Long findByName(String name) {
        log.info("class=CategoryAdapter method=findByName step=start name={}", name);

        Optional<CategoryData> data = this.repository.findByName(name);

        log.info("class=CategoryAdapter method=findByName step=end object={}", data);

        return data.map(CategoryData::getId).orElse(null);
    }
}
