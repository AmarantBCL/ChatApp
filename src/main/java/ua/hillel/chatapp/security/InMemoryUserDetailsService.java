package ua.hillel.chatapp.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.hillel.chatapp.domain.User;
import ua.hillel.chatapp.storage.InMemoryUserRepository;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InMemoryUserDetailsService implements UserDetailsService {

    InMemoryUserRepository repository;

    @Override
    public UserDetails findByUsername(String username) throws AuthenticationException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationException("User not found."));
        return new UserDetails(user.getUsername(), user.getPassword());
    }

}