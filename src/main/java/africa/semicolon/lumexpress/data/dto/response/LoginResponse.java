package africa.semicolon.lumexpress.data.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String message;
    private int code;
}
