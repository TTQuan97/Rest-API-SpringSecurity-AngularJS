package quantran.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import quantran.demo.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
