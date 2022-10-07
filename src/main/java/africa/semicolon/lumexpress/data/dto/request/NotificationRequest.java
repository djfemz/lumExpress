package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private Long userId;
    private String message;
}
