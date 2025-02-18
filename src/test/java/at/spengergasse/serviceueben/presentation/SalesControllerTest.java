package at.spengergasse.serviceueben.presentation;

import at.spengergasse.serviceueben.domain.Product;
import at.spengergasse.serviceueben.persistence.ProductRepository;
import at.spengergasse.serviceueben.service.SalesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = SalesController.class)
@AutoConfigureRestDocs
class SalesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SalesService salesService;

    @Test
    void getTopProducts_shouldReturnOk() throws Exception {
        ProductRevenueDTO dto = new ProductRevenueDTO(1L, "Laptop", 2997.0);

        when(salesService.getTopSellingProducts(5)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/sales/top-products?limit=5")
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].productName").value("Laptop"));
    }
}