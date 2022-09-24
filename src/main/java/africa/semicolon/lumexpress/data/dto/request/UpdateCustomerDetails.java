package africa.semicolon.lumexpress.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class UpdateCustomerDetails {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String imageUrl;
}
