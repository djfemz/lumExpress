package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.NotificationRequest;

public interface NotificationService {
    String send(NotificationRequest notificationRequest);
}
