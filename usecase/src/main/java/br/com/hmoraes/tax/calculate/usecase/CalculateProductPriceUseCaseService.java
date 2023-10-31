package br.com.hmoraes.tax.calculate.usecase;

import br.com.hmoraes.tax.calculate.entity.Product;
import br.com.hmoraes.tax.calculate.port.database.CategoryGateway;
import br.com.hmoraes.tax.calculate.port.database.ProductGateway;
import br.com.hmoraes.tax.calculate.port.dto.ProductInbound;
import br.com.hmoraes.tax.calculate.port.dto.ProductOutbound;
import br.com.hmoraes.tax.calculate.port.presenter.CalculateProductPricePresenter;
import br.com.hmoraes.tax.calculate.port.usecase.CalculateProductPriceUseCase;
import br.com.hmoraes.tax.calculate.usecase.mapper.ProductEntityMapper;
import br.com.hmoraes.tax.calculate.usecase.enums.CategoryTaxEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CalculateProductPriceUseCaseService implements CalculateProductPriceUseCase {

    private final ProductGateway productGateway;

    private final CategoryGateway categoryGateway;

    private final CalculateProductPricePresenter presenter;

    private final ProductEntityMapper mapper;

    @Override
    public void execute(ProductInbound inbound) {
        Product entity = this.mapper.toEntity(inbound);

        Product product = this.productGateway.findByName(entity.getName())
                .orElseGet(() -> {
                    Long categoryId = categoryGateway.findByName(entity.getCategory());

                    if (categoryId == null) {
                        throw new CategoryGateway.CategoryNotFoundException();
                    }

                    return Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name(entity.getName())
                            .basePrice(entity.getBasePrice())
                            .categoryId(categoryId)
                            .build();
                });

        product.setTaxPrice(this.calculateTaxPrice(product.getCategoryId(), product.getBasePrice()));

        Product response = this.productGateway.save(product);

        response.setCategory(this.categoryGateway.findById(response.getCategoryId()));

        ProductOutbound outbound = this.mapper.toOutbound(response);

        this.presenter.present(outbound);
    }

    private Double calculateTaxPrice(Long categoryId, Double basePrice) {
        AtomicReference<Double> taxPrice = new AtomicReference<>(basePrice);

        Arrays.stream(CategoryTaxEnum.values()).forEach(e -> {
            if(e.getId().equals(categoryId)) {
                taxPrice.set((Double) (basePrice + (basePrice * e.getIof()) + (basePrice * e.getPis()) + (basePrice * e.getCofins())));
            }
        });

        return taxPrice.get();
    }

}
