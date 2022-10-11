package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.repositories.VerificationTokenRepository;
import africa.semicolon.lumexpress.util.LumExpressUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService{

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken createToken(String email) {
        var token =VerificationToken.builder()
                .token(LumExpressUtils.generateToken())
                .createdAt(LocalDateTime.now())
                .userEmail(email)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();
        return verificationTokenRepository.save(token);
    }
}
