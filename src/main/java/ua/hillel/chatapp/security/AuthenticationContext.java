package ua.hillel.chatapp.security;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationContext {
    private final static Set<LoggedUser> users = Collections.synchronizedSet(new HashSet<>());

    public static LoggedUser getUser() {
        return users.stream()
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("User not found."));
    }

    public static void addUser(LoggedUser user) {
        users.add(user);
    }

    public static void remove(LoggedUser user) {
        users.remove(user);
    }

    public AuthenticationContext(LoggedUser user) {
        users.add(user);
    }
}
