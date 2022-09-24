package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
