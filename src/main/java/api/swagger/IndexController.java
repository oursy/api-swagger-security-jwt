package api.swagger;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class IndexController {

    @Get(produces = MediaType.TEXT_PLAIN)
    @Tag(name = "index")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "text/plain",
            schema = @Schema(type = "string")))
    public String index() {
        return "Hello Micronaut!";
    }
}
