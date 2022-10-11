package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailNotificationRequest {
    private String userEmail;
    private String mailContent;
}
