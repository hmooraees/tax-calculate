package br.com.hmoraes.tax.calculate.database;

import br.com.hmoraes.tax.calculate.database.entity.CategoryData;
import br.com.hmoraes.tax.calculate.database.mapper.CategoryEntityDataMapper;
import br.com.hmoraes.tax.calculate.database.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryAdapterTest {

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryEntityDataMapper mapper;

    @Test
    public void findById_WhenCategoryExistsAndNotExists_AssertResults() {
        Long categoryId = 1L;
        String categoryName = "TEST";

        when(this.repository.findById(categoryId)).thenReturn(Optional.of(new CategoryData(categoryId, categoryName)));

        String result = this.categoryAdapter.findById(categoryId);

        assertEquals(categoryName, result);

        verify(this.repository, times(1)).findById(categoryId);

        Long nonExistentCategoryId = 2L;
        when(this.repository.findById(nonExistentCategoryId)).thenReturn(Optional.empty());

        result = this.categoryAdapter.findById(nonExistentCategoryId);

        assertNull(result);

        verify(this.repository, times(1)).findById(nonExistentCategoryId);
    }

    @Test
    public void findByName_WhenCategoryExistsAndNotExists_AssertResults() {
        String categoryName = "TEST";
        Long categoryId = 1L;

        when(this.repository.findByName(categoryName)).thenReturn(Optional.of(new CategoryData(categoryId, categoryName)));

        Long result = this.categoryAdapter.findByName(categoryName);

        assertEquals(categoryId, result);

        verify(this.repository, times(1)).findByName(categoryName);

        String categoryNotExists = "TEST N";
        when(this.repository.findByName(categoryNotExists)).thenReturn(Optional.empty());

        result = this.categoryAdapter.findByName(categoryNotExists);

        assertNull(result);

        verify(this.repository, times(1)).findByName(categoryNotExists);
    }
}
