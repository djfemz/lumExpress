package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
