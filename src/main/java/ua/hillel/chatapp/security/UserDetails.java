package ua.hillel.chatapp.security;

/**
 * Really existing user in the source and authenticated.
 */
public record UserDetails(String username, String password, UserRole role) {
}