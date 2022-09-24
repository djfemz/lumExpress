package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    private String email;
    private String password;
}
