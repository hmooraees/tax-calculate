package br.com.hmoraes.tax.calculate.database.mapper;

import br.com.hmoraes.tax.calculate.database.entity.CategoryData;
import br.com.hmoraes.tax.calculate.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityDataMapper {

    CategoryData toData(Category entity);

    Category toEntity(CategoryData data);
}
