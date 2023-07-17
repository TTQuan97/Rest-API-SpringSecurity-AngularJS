package quantran.demo.service;

import quantran.demo.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAll();

    public Category getById(Integer id);
}
