package quantran.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import quantran.demo.entity.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
}
