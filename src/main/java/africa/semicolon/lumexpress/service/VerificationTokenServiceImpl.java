package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService{
    @Override
    public VerificationToken createToken() {
        return null;
    }
}
