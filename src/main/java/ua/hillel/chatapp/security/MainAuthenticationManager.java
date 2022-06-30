package ua.hillel.chatapp.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainAuthenticationManager implements AuthenticationManager {
    Logger logger = LogManager.getLogger(MainAuthenticationManager.class);

    List<Authenticator> authenticators = new ArrayList<>() {{
        new CredentialsAuthenticator(service);
        new RoleAuthenticator(service);
    }};
    UserDetailsService service;

    public void doFilter(Socket socket, AuthenticationRequest request) throws IOException {
        try {
            for (Authenticator authenticator : authenticators) {
                authenticator.attemptAuthenticate(request);
            }
        } catch (AuthenticationException e) {
            logger.debug(request.username() + " could not authenticate.");
        }
    }
}
