package ua.hillel.chatapp.security;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode
@ToString
public class LoggedUser {
    String username;
    String password;
    UserRole role;

    public LoggedUser(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
