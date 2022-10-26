package africa.semicolon.lumexpress.security.providers;

import africa.semicolon.lumexpress.security.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        if (userDetails!=null) {
            if (isPasswordMatch(authentication, userDetails)){
                UsernamePasswordAuthenticationToken authenticatedToken =
                        new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                                authentication.getCredentials(),
                                userDetails.getAuthorities());
                return authenticatedToken;
            }
            throw new BadCredentialsException("password incorrect");
        }
        throw new AuthenticationCredentialsNotFoundException("email does not exist");
    }

    private boolean isPasswordMatch(Authentication authentication, UserDetails userDetails) {
        return passwordEncoder.matches((String) authentication.getCredentials(),
                userDetails.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        Class<UsernamePasswordAuthenticationToken> appAuthType = UsernamePasswordAuthenticationToken.class;
        return authentication.equals(appAuthType);
    }
}
