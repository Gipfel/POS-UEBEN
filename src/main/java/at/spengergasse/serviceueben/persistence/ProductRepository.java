package at.spengergasse.serviceueben.persistence;

import at.spengergasse.serviceueben.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}