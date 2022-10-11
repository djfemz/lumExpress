package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class GmailNotificationServiceImpl implements EmailNotificationService {

    private final JavaMailSender javaMailSender;

    @Override
    public String sendHtmlMail(EmailNotificationRequest emailNotification) {
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("no-reply@email.lumExpress.com.ng");
            mimeMessageHelper.setTo(emailNotification.getUserEmail());
            mimeMessageHelper.setText(emailNotification.getMailContent(), true);
            javaMailSender.send(mimeMessage);
            return String.format("email sent to %s successfully",
                    emailNotification.getUserEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return String.format("email not sent to %s", emailNotification.getUserEmail());
    }
}
