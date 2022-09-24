package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
