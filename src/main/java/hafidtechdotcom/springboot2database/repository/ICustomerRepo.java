package hafidtechdotcom.springboot2database.repository;

import hafidtechdotcom.springboot2database.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
}
