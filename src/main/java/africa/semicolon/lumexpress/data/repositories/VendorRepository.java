package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Admin;
import africa.semicolon.lumexpress.data.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByEmail(String email);

}
