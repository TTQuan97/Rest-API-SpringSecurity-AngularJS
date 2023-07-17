package quantran.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import quantran.demo.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
