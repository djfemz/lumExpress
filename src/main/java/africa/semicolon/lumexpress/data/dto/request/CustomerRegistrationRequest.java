package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Validated
public class CustomerRegistrationRequest {
    @NotNull(message = "na bush dem born you?")
    @NotEmpty(message = "country cannot be empty")
    private String country;
    @Email(message = "invalid email")
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotNull(message = "provide name or getat")
    @NotEmpty(message = "provide name or getat")
    private String firstName;
    @NotNull
    @NotEmpty
    private String password;
}
