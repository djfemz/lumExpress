package africa.semicolon.lumexpress.security;

import africa.semicolon.lumexpress.data.models.Authority;
import africa.semicolon.lumexpress.data.models.LumExpressUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class SecureUser implements UserDetails {
    private final LumExpressUser user;

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities =
                new ArrayList<>();
        user.getAuthorities().forEach(authority ->
                addUserAuthoritiesToAuthoritiesList(authorities, authority));
        return authorities;
    }

    private static void addUserAuthoritiesToAuthoritiesList(List<SimpleGrantedAuthority> authorities, Authority authority) {
        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(authority.name());
        authorities.add(simpleGrantedAuthority);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
