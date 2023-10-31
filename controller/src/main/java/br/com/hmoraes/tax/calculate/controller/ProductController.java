package br.com.hmoraes.tax.calculate.controller;

import br.com.hmoraes.tax.calculate.controller.dto.ProductRequest;
import br.com.hmoraes.tax.calculate.controller.dto.ProductResponse;
import br.com.hmoraes.tax.calculate.controller.mapper.ProductRequestResponseMapper;
import br.com.hmoraes.tax.calculate.port.dto.ProductInbound;
import br.com.hmoraes.tax.calculate.port.presenter.CalculateProductPricePresenter;
import br.com.hmoraes.tax.calculate.port.usecase.CalculateProductPriceUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRequestResponseMapper mapper;

    private final CalculateProductPriceUseCase useCase;

    private final CalculateProductPricePresenter presenter;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductResponse> execute(@Valid @RequestBody ProductRequest request) {

        ProductInbound inbound = this.mapper.toInBound(request);

        this.useCase.execute(inbound);

        ProductResponse response = this.mapper.toResponse(this.presenter.getView());

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);

    }
}
