package africa.semicolon.lumexpress.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailNotificationRequest {
    private String userEmail;
    private String mailContent;
}
