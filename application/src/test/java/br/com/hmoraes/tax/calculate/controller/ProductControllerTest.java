package br.com.hmoraes.tax.calculate.controller;

import br.com.hmoraes.tax.calculate.TaxCalculateApplication;
import br.com.hmoraes.tax.calculate.controller.dto.ProductRequest;
import br.com.hmoraes.tax.calculate.controller.mapper.ProductRequestResponseMapper;
import br.com.hmoraes.tax.calculate.port.presenter.CalculateProductPricePresenter;
import br.com.hmoraes.tax.calculate.port.usecase.CalculateProductPriceUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProductController.class})
@ContextConfiguration(classes = TaxCalculateApplication.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductRequestResponseMapper mapper;

    @MockBean
    private CalculateProductPriceUseCase useCase;

    @MockBean
    private CalculateProductPricePresenter presenter;

    @Test
    public void post_WhenExecuteRestProduct_ExpectedCreateAndContentTypeJson() throws Exception {
        ProductRequest request = ProductRequest.builder()
                .name("TEST")
                .category("TEST")
                .basePrice(10.0)
                .build();

        String requestJson = this.objectMapper.writeValueAsString(request);

        ResultActions result = this.mockMvc.perform(post("/v1/api/products")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().is4xxClientError());
    }
}
