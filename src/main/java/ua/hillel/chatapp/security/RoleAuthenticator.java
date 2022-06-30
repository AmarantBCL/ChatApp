package ua.hillel.chatapp.security;

import java.util.Objects;

public class RoleAuthenticator extends AbstractAuthenticator {
    public RoleAuthenticator(UserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    public void attemptAuthenticate(AuthenticationRequest request) {
        UserDetails details = getUserDetailsService().findByUsername(request.username());
        if (!Objects.equals(details.role(), request.role())) {
            throw new AuthenticationException("Wrong role.");
        }
    }
}
