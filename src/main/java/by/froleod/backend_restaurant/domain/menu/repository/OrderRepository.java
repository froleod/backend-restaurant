package by.froleod.backend_restaurant.domain.menu.repository;

import by.froleod.backend_restaurant.domain.menu.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
