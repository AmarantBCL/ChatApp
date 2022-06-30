package ua.hillel.chatapp.security;

public interface UserDetailsService {
    UserDetails findByUsername(String username) throws AuthenticationException;
}