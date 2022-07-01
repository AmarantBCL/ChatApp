package ua.hillel.chatapp.logic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import ua.hillel.chatapp.security.CredentialUtils;

import static org.assertj.core.api.Assertions.catchRuntimeException;

public class CredentialUtilsTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"-u=<username> -p=<password>", "-log-in -u=<username> -p=<password>", "login -u=<username> -p=<password>"})
    void shouldThrowRuntimeException_whenCredentialLineDoesNotStartWithLoginCommand(String invalidCredentialLine) {
        Assertions.assertThat(
                catchRuntimeException(() -> CredentialUtils.parse(invalidCredentialLine))
        ).isNotNull();
    }

    @Test
    void shouldRetrieveParsedUserCredentials_asArrayContainingFirstValueAsUsername_andSecondValueAsPassword() {
        Assertions.assertThat(CredentialUtils.parse("-login -u=username -p=password"))
                .isNotEmpty()
                .containsExactly("username", "password", "");
    }

    @Test
    void shouldRetrieveParsedSuperUserCredentials_asArrayContainingFirstValueAsUsername_andSecondValueAsPassword_andSuperUserFlag() {
        Assertions.assertThat(CredentialUtils.parse("-login -u=username -p=password -su"))
                .isNotEmpty()
                .containsExactly("username", "password", "-su");
    }
}
