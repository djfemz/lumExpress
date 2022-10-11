package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;

public interface EmailNotificationService {
    String sendHtmlMail(EmailNotificationRequest emailNotification);
}
