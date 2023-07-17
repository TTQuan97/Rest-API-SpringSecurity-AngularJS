package quantran.demo.service.serviceimpl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quantran.demo.dao.ProductDao;
import quantran.demo.entity.OrderDetail;
import quantran.demo.entity.Product;
import quantran.demo.service.CartService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private HttpSession session;

    List<OrderDetail> cart = new ArrayList<>();

    @Override
    public List<OrderDetail> getCart() {
        return cart;
    }

    @Override
    public int countItem() {
        int totalItemInCart = 0;
        for (OrderDetail obj : cart) {
            totalItemInCart += obj.getQuantity();
        }
        return totalItemInCart;
    }

    @Override
    public BigDecimal countTotalMonney() {
        double totalMonney = 0;
        for (OrderDetail obj : cart) {
            totalMonney += (obj.getPrice() * obj.getQuantity());
        }
        return new BigDecimal(totalMonney).setScale(1, RoundingMode.HALF_EVEN);
    }

    @Override
    public void add(Integer productId) {

        OrderDetail orderDetail = cart.stream().filter(obj -> obj.getProduct().getId().equals(productId)).
                findFirst().orElse(null);
        if (orderDetail != null) {
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
        } else {
            Product product = productDao.findById(productId).get();
            if (product != null) {
                cart.add(new OrderDetail(null, null, product, product.getPrice(), 1));
            }
        }
        //session.setAttribute("cart", cart);
    }

    @Override
    public void update(Integer productId, Integer quantity) {
        OrderDetail orderDetail = cart.stream().filter(obj -> obj.getProduct().getId().equals(productId)).
                findFirst().orElse(null);
        if (orderDetail != null && quantity > 0) {
            orderDetail.setQuantity(quantity);
            //session.setAttribute("cart", cart);
        }

    }

    @Override
    public void delete(Integer productId) {
        cart.removeIf(obj -> obj.getProduct().getId().equals(productId));
        //session.setAttribute("cart", cart);
    }

    @Override
    public void clear() {
        cart.clear();
        //session.setAttribute("cart", cart);
    }
}
