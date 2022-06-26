package ua.hillel.chatapp.security;

import java.util.Objects;

public class CredentialsAuthenticator extends AbstractAuthenticator {

    public CredentialsAuthenticator(UserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    public void attemptAuthenticate(AuthenticationRequest request) {
        UserDetails details = getUserDetailsService().findByUsername(request.username());
        if (!Objects.equals(details.password(), request.password())) {
            throw new AuthenticationException("Wrong credentials.");
        }
    }

}