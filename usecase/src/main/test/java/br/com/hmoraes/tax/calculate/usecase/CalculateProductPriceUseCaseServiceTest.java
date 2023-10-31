package br.com.hmoraes.tax.calculate.usecase;

import br.com.hmoraes.tax.calculate.entity.Product;
import br.com.hmoraes.tax.calculate.port.database.CategoryGateway;
import br.com.hmoraes.tax.calculate.port.database.ProductGateway;
import br.com.hmoraes.tax.calculate.port.dto.ProductInbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import br.com.hmoraes.tax.calculate.port.presenter.CalculateProductPricePresenter;
import br.com.hmoraes.tax.calculate.usecase.mapper.ProductEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateProductPriceUseCaseServiceTest {

    @InjectMocks
    private CalculateProductPriceUseCaseService useCase;

    @Mock
    private ProductGateway productGateway;

    @Mock
    private CategoryGateway categoryGateway;

    @Mock
    private CalculateProductPricePresenter presenter;

    @Mock
    private ProductEntityMapper mapper;

    @Test
    public void execute_WhenProductExists_Verify() {
        String id = UUID.randomUUID().toString();

        ProductInbound inbound = ProductInbound.builder()
                .name("TEST")
                .category("TEST")
                .basePrice(100.0)
                .build();

        Product entity = Product.builder()
                .id(id)
                .name("TEST")
                .categoryId(1L)
                .category("TEST")
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        Product product = Product.builder()
                .id(id)
                .name("TEST")
                .categoryId(1L)
                .category("TEST")
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        ProductOutbound outbound = ProductOutbound.builder()
                .id(id)
                .name("TEST")
                .category("TEST")
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        when(this.mapper.toEntity(inbound)).thenReturn(entity);
        when(this.productGateway.findByName(entity.getName())).thenReturn(Optional.of(product));
        when(this.mapper.toOutbound(product)).thenReturn(outbound);
        when(this.productGateway.save(any())).thenReturn(product);

        this.useCase.execute(inbound);

        verify(this.productGateway, times(1)).save(any());
        verify(this.presenter, times(1)).present(outbound);
    }

    @Test
    public void execute_WhenCategoryNotFound_ThrowCategoryNotFoundException() {
        String id = UUID.randomUUID().toString();

        ProductInbound inbound = ProductInbound.builder()
                .name("TEST")
                .category("TEST")
                .basePrice(100.0)
                .build();

        Product entity = Product.builder()
                .id(id)
                .name("TEST")
                .categoryId(1L)
                .category("TEST")
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        when(this.mapper.toEntity(inbound)).thenReturn(entity);
        when(this.productGateway.findByName(entity.getName())).thenReturn(Optional.empty());
        when(this.categoryGateway.findByName(entity.getCategory())).thenReturn(null);

        assertThrows(CategoryGateway.CategoryNotFoundException.class, () -> {
            useCase.execute(inbound);
        });

        verify(this.productGateway, never()).save(any());
        verify(this.presenter, never()).present(any());
    }

    @Test
    public void execute_WhenCalculateTaxPrice_ReturnTaxPrice() {
        String id = UUID.randomUUID().toString();

        ProductInbound inbound = ProductInbound.builder()
                .name("TEST")
                .category("TEST")
                .basePrice(100.0)
                .build();

        Product entity = Product.builder()
                .id(id)
                .name("TEST")
                .categoryId(1L)
                .category("TEST")
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        Product product = Product.builder()
                .id(id)
                .name("TEST")
                .categoryId(1L)
                .category("TEST")
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        ProductOutbound outbound = ProductOutbound.builder()
                .id(id)
                .name("TEST")
                .category("TEST")
                .basePrice(100.0)
                .taxPrice(106.0)
                .build();

        when(this.mapper.toEntity(inbound)).thenReturn(entity);
        when(this.productGateway.findByName(entity.getName())).thenReturn(Optional.empty());
        when(this.categoryGateway.findByName(entity.getCategory())).thenReturn(entity.getCategoryId());
        when(this.productGateway.save(any())).thenReturn(product);
        when(this.mapper.toOutbound(product)).thenReturn(outbound);

        this.useCase.execute(inbound);

        verify(this.productGateway, times(1)).save(any());
        verify(this.presenter, times(1)).present(outbound);

        assertEquals(outbound.getTaxPrice(), product.getTaxPrice());
    }
}