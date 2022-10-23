package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exception.VerificationTokenException;
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

    private VerificationToken verificationToken;
    @BeforeEach
    void setUp() {
        verificationToken = verificationTokenService
                .createToken("testuser@email.com");
    }

    @Test
    void createTokenTest() {
       log.info("verification token object-->{}", verificationToken);
       assertThat(verificationToken).isNotNull();
       assertThat(verificationToken.getUserEmail()).isEqualTo("testuser@email.com");
       assertThat(verificationToken.getToken().length()).isEqualTo(5);

    }

    @Test
    void isValidVerificationTokenTest() throws VerificationTokenException {
        assertThat(verificationToken).isNotNull();
        var response =
                verificationTokenService
                        .isValidVerificationToken(verificationToken.getToken());
        assertThat(response).isTrue();
    }
}