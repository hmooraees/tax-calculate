package br.com.hmoraes.tax.calculate.presenter;

import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductView;
import br.com.hmoraes.tax.calculate.presenter.mapper.ProductViewMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateProductPricePresenterServiceTest {

    @InjectMocks
    private CalculateProductPricePresenterService presenter;

    @Mock
    private ProductViewMapper mapper;

    @Test
    public void presenter_WhenOutboundPresent_ReturnView() {
        String id = UUID.randomUUID().toString();

        ProductOutbound outbound = ProductOutbound.builder()
                .id(id)
                .name("TEST")
                .category("TEST")
                .basePrice(1.0)
                .taxPrice(1.0)
                .build();

        ProductView expectedView = ProductView.builder()
                .id(id)
                .name("TEST")
                .category("TEST")
                .basePrice(1.0)
                .taxPrice(1.0)
                .build();

        when(this.mapper.toView(outbound)).thenReturn(expectedView);

        this.presenter.present(outbound);

        assertEquals(expectedView, this.presenter.getView());
    }
}
