package ua.hillel.chatapp.security;

public interface Authenticator {
    void attemptAuthenticate(AuthenticationRequest request);
}