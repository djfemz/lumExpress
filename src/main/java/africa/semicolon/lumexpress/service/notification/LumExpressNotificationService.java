package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.NotificationRequest;

public interface LumExpressNotificationService {
    String send(NotificationRequest notificationRequest);
}
