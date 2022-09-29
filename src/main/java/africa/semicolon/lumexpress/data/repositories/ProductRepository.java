package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Page<Product> findAll(Pageable pageable);

}
