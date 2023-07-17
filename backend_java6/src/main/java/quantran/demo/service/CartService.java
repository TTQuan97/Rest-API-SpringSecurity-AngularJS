package quantran.demo.service;

import quantran.demo.entity.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    List<OrderDetail> getCart();

    int countItem();

    BigDecimal countTotalMonney();

    void add(Integer productId);

    void update(Integer productId, Integer quantity);

    void delete(Integer productId);

    void clear();

}
