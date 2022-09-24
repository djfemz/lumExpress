package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerRegistrationRequest {
    private String country;
    private String email;
    private String password;
}
