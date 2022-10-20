package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.util.LumExpressUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    private CustomerRegistrationRequest request;
    @BeforeEach
    void setUp() {
        request = CustomerRegistrationRequest
                .builder()
                .email("tedoj24915@inkmoto.com")
                .password("test Password")
                .country("Nigeria")
                .build();


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerCustomerTest() {
        CustomerRegistrationResponse customerRegistrationResponse=
                customerService.register(request);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage())
                .isNotNull();
        assertThat(customerRegistrationResponse.getUserId())
                .isGreaterThan(0);
        assertThat(customerRegistrationResponse.getCode())
                .isEqualTo(201);
    }

    @Test
    void updateProfileTest() {
        CustomerRegistrationResponse customerRegistrationResponse=
                customerService.register(request);
        assertThat(customerRegistrationResponse).isNotNull();
        UpdateCustomerDetails details = UpdateCustomerDetails
                .builder()
                .customerId(customerRegistrationResponse.getUserId())
                .imageUrl(LumExpressUtils.getMockCloudinaryImageUrl())
                .lastName("test lastName")
                .city("Yaba")
                .street("Herbert Macaulay")
                .state("Lagos")
                .buildingNumber(312)
                .phoneNumber("99999999999")
                .build();
        var updateResponse = customerService
                .completeCustomerProfile(details);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.contains("success")).isTrue();
    }
}