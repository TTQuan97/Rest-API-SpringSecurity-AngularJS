package quantran.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quantran.demo.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.category.id = ?1")
    Page<Product> getAllByCategory(String categoryId, Pageable pageable);
}
