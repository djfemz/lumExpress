package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.repositories.VerificationTokenRepository;
import africa.semicolon.lumexpress.exception.VerificationTokenException;
import africa.semicolon.lumexpress.util.LumExpressUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Override
    public boolean isValidVerificationToken(String token) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token)
                        .orElseThrow(()->new VerificationTokenException("token not found"));
        if (isTokenNotExpired(verificationToken)) return true;
        throw new VerificationTokenException("token has expired");
    }

    private boolean isTokenNotExpired(VerificationToken verificationToken) {
        return LocalDateTime.now().isBefore(verificationToken.getExpiresAt());
    }
}
