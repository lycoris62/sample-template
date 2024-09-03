package ex.sample.global.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Components components = new Components();

        return new OpenAPI()
            .info(info())
            .components(components);
    }

    private Info info() {
        return new Info()
            .title("API 명세서")
            .version("0.0.1");
    }
}
