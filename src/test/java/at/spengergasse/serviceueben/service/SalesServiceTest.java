package at.spengergasse.serviceueben.service;

import at.spengergasse.serviceueben.domain.Order;
import at.spengergasse.serviceueben.domain.Product;
import at.spengergasse.serviceueben.persistence.OrderRepository;
import at.spengergasse.serviceueben.presentation.ProductRevenueDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private SalesService salesService;

    @Test
    void whenNoOrders_thenReturnEmptyList() {
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductRevenueDTO> result = salesService.getTopSellingProducts(5);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldCalculateRevenueCorrectly() {
        Product p1 = new Product(1L, "Laptop", "...");
        Order o1 = new Order(1L, p1, 2, 999.0);
        Order o2 = new Order(2L, p1, 1, 999.0);

        when(orderRepository.findAll()).thenReturn(List.of(o1, o2));

        List<ProductRevenueDTO> result = salesService.getTopSellingProducts(5);

        assertEquals(1, result.size());
        assertEquals(2997.0, result.getFirst().getTotalRevenue(), 0.01);
    }
}