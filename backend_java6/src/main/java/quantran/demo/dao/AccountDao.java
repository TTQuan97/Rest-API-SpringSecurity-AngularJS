package quantran.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import quantran.demo.entity.Account;

public interface AccountDao extends JpaRepository<Account, String> {

}
