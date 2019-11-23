package api.swagger;

import api.swagger.domain.User;
import api.swagger.repository.UserRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.data.jdbc.config.SchemaGenerator;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
@Requires(beans = {DataSource.class, SchemaGenerator.class})
public class DataSourceInitConfig {

    private final UserRepository userRepository;


    @Inject
    public DataSourceInitConfig(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @EventListener
    public void onstartUp(ServerStartupEvent serverStartupEvent) {
        User user = new User("admin", "admin");
        userRepository.save(user);
    }
}
