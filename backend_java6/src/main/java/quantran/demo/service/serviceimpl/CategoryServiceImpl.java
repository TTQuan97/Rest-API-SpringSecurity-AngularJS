package quantran.demo.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quantran.demo.dao.CategoryDao;
import quantran.demo.entity.Category;
import quantran.demo.service.CategoryService;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category getById(Integer id) {
        return categoryDao.findById(id).get();
    }

}
