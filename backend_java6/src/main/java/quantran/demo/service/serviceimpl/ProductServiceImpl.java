package quantran.demo.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import quantran.demo.dao.ProductDao;
import quantran.demo.entity.Product;
import quantran.demo.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Page<Product> getAll(Integer indexPage) {
        Pageable pageable = PageRequest.of(indexPage < 0 ? 0 : indexPage, 6);
        return productDao.findAll(pageable);
    }

    @Override
    public Page<Product> getAllByCategory(String categoryId, Integer indexPage) {
        Pageable pageable = PageRequest.of(indexPage < 0 ? 0 : indexPage, 6);
        return productDao.getAllByCategory(categoryId, pageable);
    }

    @Override
    public Product getById(Integer id) {
        return productDao.findById(id).get();
    }

}
