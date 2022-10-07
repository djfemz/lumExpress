package africa.semicolon.lumexpress.service.notification;

public interface EmailSender {
    String sendHtmlMail(EmailDetails emailDetails);
}
