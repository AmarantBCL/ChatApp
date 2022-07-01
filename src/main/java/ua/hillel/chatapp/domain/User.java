package ua.hillel.chatapp.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ua.hillel.chatapp.security.UserRole;

@Value
@EqualsAndHashCode
@ToString
public class User {
    Long id;
    String username;
    String password;
    UserRole role;

    public User(Long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, UserRole role) {
        this(null, username, password, role);
    }
}