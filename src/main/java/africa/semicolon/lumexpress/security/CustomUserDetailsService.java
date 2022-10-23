package africa.semicolon.lumexpress.security;

import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.exception.LumExpressException;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import africa.semicolon.lumexpress.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        LumExpressUser user = null;
        try {
            user = userService.getUserByUsername(username);
            return new SecureUser(user);
        } catch (UserNotFoundException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
