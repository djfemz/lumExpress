package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class VerificationTokenServiceImplTest {
    @Autowired
    private VerificationTokenService verificationTokenService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createTokenTest() {
       VerificationToken verificationToken = verificationTokenService
               .createToken("testuser@email.com");
       log.info("verification token object-->{}", verificationToken);
       assertThat(verificationToken).isNotNull();
       assertThat(verificationToken.getUserEmail()).isEqualTo("testuser@email.com");
       assertThat(verificationToken.getToken().length()).isEqualTo(5);

    }
}