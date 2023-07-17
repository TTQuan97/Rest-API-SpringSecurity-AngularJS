package quantran.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import quantran.demo.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
