package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
