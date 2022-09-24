package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
