package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exception.VerificationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class VerificationTokenRepositoryTest {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationToken verificationToken;

    @BeforeEach
    void setUp() {
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@email.com");
        verificationTokenRepository.deleteAll();
    }

    @Test
    void findByUserEmailTest() throws VerificationTokenException {
        verificationTokenRepository.save(verificationToken);
        VerificationToken foundToken =
                verificationTokenRepository.findByUserEmail("test@email.com")
                        .orElseThrow(()->new VerificationTokenException(
                                "token not found"));
        log.info("found token--->{}", foundToken);
        assertThat(foundToken).isNotNull();
        assertThat(foundToken.getToken())
                .isEqualTo(verificationToken.getToken());
    }

    @Test
    void findByTokenTest() throws VerificationTokenException {
        verificationTokenRepository.save(verificationToken);
        VerificationToken token = verificationTokenRepository.findByToken(verificationToken
                        .getToken())
                .orElseThrow(()->new VerificationTokenException("token not found"));
        assertThat(token).isNotNull();
        assertThat(token.getToken()).isEqualTo("12345");
    }
}