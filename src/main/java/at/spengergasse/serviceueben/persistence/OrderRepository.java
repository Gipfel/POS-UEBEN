package at.spengergasse.serviceueben.persistence;

import at.spengergasse.serviceueben.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}