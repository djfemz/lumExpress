package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GmailEmailLumExpressNotificationServiceImplTest {

    @Autowired
    private EmailNotificationService emailSender;

    @Test
    void sendHtmlMailTest() {
        EmailNotificationRequest emailNotification = new EmailNotificationRequest();
        emailNotification.setUserEmail("oladejifemi00@gmail.com");
        emailNotification.setMailContent("Hi, whimp!!");
        String response =
                emailSender.sendHtmlMail(emailNotification);
        assertThat(response.contains("successfully")).isTrue();
    }
}