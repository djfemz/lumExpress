package africa.semicolon.lumexpress.security.jwt;

import africa.semicolon.lumexpress.data.models.LumExpressUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
public class JwtUtil {
    private String issuer;
    private Algorithm algorithm;


    public String generateAccessToken(UserDetails user){
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateRefreshToken(UserDetails user){
        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withSubject(user.getUsername())
                .sign(algorithm);
    }
}
