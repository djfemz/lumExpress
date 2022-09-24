package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        CustomerRegistrationRequest request = CustomerRegistrationRequest
                                                .builder()
                                                .email("test@gmail.com")
                                                .password("test Password")
                                                .country("Nigeria")
                                                .build();
    }

    @Test
    void login() {
    }

    @Test
    void completeProfile() {
    }
}