package at.spengergasse.serviceueben.service;

import at.spengergasse.serviceueben.domain.Order;
import at.spengergasse.serviceueben.domain.Product;
import at.spengergasse.serviceueben.persistence.OrderRepository;
import at.spengergasse.serviceueben.presentation.ProductRevenueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final OrderRepository orderRepository;

    public List<ProductRevenueDTO> getTopSellingProducts(int limit) {
        return orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getTotalPrice)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                .limit(limit)
                .map(entry -> new ProductRevenueDTO(
                        entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }
}