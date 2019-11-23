package api.swagger;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Micronaut Api Security JWT Swagger",
                version = "0.1",
                description = "Micronaut Api Security JWT Swagger Demo",
                license = @License(name = "Apache 2.0", url = "http://foo.bar")
        )
)
@SecurityScheme(name = "apiKey",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER, paramName = "Authorization")
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

}