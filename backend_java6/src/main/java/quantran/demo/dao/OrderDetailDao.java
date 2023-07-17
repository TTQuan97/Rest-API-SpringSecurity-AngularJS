package quantran.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import quantran.demo.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {
}
