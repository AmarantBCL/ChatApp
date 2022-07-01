package ua.hillel.chatapp.storage;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.hillel.chatapp.domain.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class InMemoryUserRepository implements Repository<User> {
    Set<User> users;

    public void add(User user) {
        users.add(user);
    }

    public void add(Collection<User> users) {
        this.users.addAll(users);
    }

    @Override
    public Collection<User> findAll() {
        return users;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();
    }
}