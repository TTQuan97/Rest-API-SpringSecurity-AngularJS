package quantran.demo.service;

import org.springframework.data.domain.Page;
import quantran.demo.entity.Product;


public interface ProductService {

    Page<Product> getAll(Integer indexPage);

    Page<Product> getAllByCategory(String categoryId, Integer indexPage);

    Product getById(Integer id);
}
