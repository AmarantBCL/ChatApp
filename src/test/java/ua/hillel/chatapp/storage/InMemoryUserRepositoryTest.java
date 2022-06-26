package ua.hillel.chatapp.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.hillel.chatapp.domain.User;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InMemoryUserRepositoryTest {

    InMemoryUserRepository repository;
    Set<User> mockUserRegistry;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository(mockUserRegistry = mock(Set.class));
    }

    @Test
    void shouldAddNewUser_intoInMemoryRepository() {
        User newUser = new User("Maks", "qwerty");
        repository.add(newUser);
        verify(mockUserRegistry, times(1)).add(newUser);
    }

    @Test
    void shouldAddNewUsers_intoInMemoryRepository() {
        List<User> newUsers = List.of(new User("Maks", "qwerty"), new User("Dima", "123456"));
        repository.add(newUsers);
        verify(mockUserRegistry, times(1)).addAll(newUsers);
    }

//    @Test
//    void shouldFetchExistingUser_byUsernameAndLogin_whenRequestedFromInMemoryRepository() {
//        repository = new InMemoryUserRepository(mockUserRegistry = Set.of(new User("Maks", "qwerty")));
//        assertThat(repository.findByUsernameAndPassword("Maks", "qwerty")).isNotEmpty();
//    }
//
//    @Test
//    void shouldFetchNothing_whenRequestedUser_byUsernameAndLogin_doesNotExistInMemoryRepository() {
//        assertThat(repository.findByUsernameAndPassword("Unknown", "qwerty")).isEmpty();
//    }
}