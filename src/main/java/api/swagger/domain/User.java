package api.swagger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.data.annotation.*;
import io.micronaut.data.jdbc.annotation.JoinTable;
import io.micronaut.data.model.naming.NamingStrategies;

import java.util.Set;

@MappedEntity(value = "USER", namingStrategy = NamingStrategies.UnderScoreSeparatedUpperCase.class)
public class User {


    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String password;


    public User(String login, String password) {
        this.login = login;
        this.password = password;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
