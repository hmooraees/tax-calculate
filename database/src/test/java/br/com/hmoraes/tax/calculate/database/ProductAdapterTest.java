package br.com.hmoraes.tax.calculate.database;

import br.com.hmoraes.tax.calculate.database.entity.ProductData;
import br.com.hmoraes.tax.calculate.database.mapper.ProductEntityDataMapper;
import br.com.hmoraes.tax.calculate.database.repository.ProductRepository;
import br.com.hmoraes.tax.calculate.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductAdapterTest {

    @InjectMocks
    private ProductAdapter productAdapter;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductEntityDataMapper mapper;

    @Test
    public void findByName_WhenProductExistsAndNotExists_AssertResults() {
        String id = UUID.randomUUID().toString();

        ProductData productData = ProductData.builder()
                .id(id)
                .name("TEST")
                .categoryId(1L)
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        when(this.repository.findByName(productData.getName())).thenReturn(Optional.of(productData));

        Product product = Product.builder()
                .id(id)
                .name(productData.getName())
                .categoryId(productData.getCategoryId())
                .basePrice(productData.getBasePrice())
                .taxPrice(productData.getTaxPrice())
                .build();

        Optional<Product> result = this.productAdapter.findByName(productData.getName());

        assertTrue(result.isPresent());
        assertEquals(product, result.get());

        verify(this.repository, times(1)).findByName(productData.getName());

        String productNotExists = "TEST N";

        when(this.repository.findByName(productNotExists)).thenReturn(Optional.empty());

        result = this.productAdapter.findByName(productNotExists);

        assertFalse(result.isPresent());

        verify(this.repository, times(1)).findByName(productNotExists);
    }

    @Test
    public void save_WhenSaveProduct_ExpectedProduct() {
        Product product = Product.builder()
                .name("TEST")
                .categoryId(1L)
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        ProductData productData = ProductData.builder()
                .name("TEST")
                .categoryId(1L)
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        when(this.mapper.toData(product)).thenReturn(productData);

        Product savedProduct = Product.builder()
                .id(UUID.randomUUID().toString())
                .name("TEST")
                .categoryId(1L)
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        when(this.repository.save(productData)).thenReturn(productData);
        when(this.mapper.toEntity(productData)).thenReturn(savedProduct);

        Product result = this.productAdapter.save(product);

        assertEquals(savedProduct, result);

        verify(this.repository, times(1)).save(productData);
    }
}