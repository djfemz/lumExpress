package africa.semicolon.lumexpress.service.notification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GmailEmailSenderImplTest {

    @Autowired
    private EmailSender emailSender;

    @Test
    void sendHtmlMailTest() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setUserEmail("oladejifemi00@gmail.com");
        emailDetails.setMailContent("Hi, whimp!!");
        String response =
                emailSender.sendHtmlMail(emailDetails);
        assertThat(response.contains("successfully")).isTrue();
    }
}