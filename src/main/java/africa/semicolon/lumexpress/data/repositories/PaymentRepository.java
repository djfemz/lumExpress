package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
