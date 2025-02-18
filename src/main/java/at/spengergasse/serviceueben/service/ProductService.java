package at.spengergasse.serviceueben.service;

import at.spengergasse.serviceueben.domain.Product;
import at.spengergasse.serviceueben.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }
}