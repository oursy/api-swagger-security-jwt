package api.swagger.security;

import api.swagger.domain.User;
import api.swagger.repository.UserRepository;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.security.authentication.providers.AuthoritiesFetcher;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import java.util.Arrays;
import java.util.Optional;


@Factory
public class AuthenticationProviderUserPassword {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String rawPassword) {
                return rawPassword.toLowerCase();
            }

            @Override
            public boolean matches(String rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }

    @Bean
    UserFetcher userFetcher(UserRepository userRepository) {
        return username -> {
            Optional<User> userOptional = userRepository.findByLogin(username);
            return userOptional.<Publisher<UserState>>map(user -> Flowable.just(new UserState() {
                @Override
                public String getUsername() {
                    return user.getLogin();
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }

                @Override
                public boolean isAccountExpired() {
                    return false;
                }

                @Override
                public boolean isAccountLocked() {
                    return false;
                }

                @Override
                public boolean isPasswordExpired() {
                    return false;
                }
            })).orElse(Flowable.empty());
        };
    }

    @Bean
    AuthoritiesFetcher authoritiesFetcher() {
        return username -> Flowable.just(Arrays.asList("ROLE_ADMIN"));
    }
}
