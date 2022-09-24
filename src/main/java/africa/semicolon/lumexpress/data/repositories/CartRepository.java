package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
