package id.my.hendisantika.springbootopensearch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:02
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Spring Data OpenSearch example")
                .description("Spring Data OpenSearch example with Testcontainers")
                .version("v0.0.2")
                .contact(getContactDetails()));
    }

    private Contact getContactDetails() {
        return new Contact().name("Hendi Santika")
                .email("hendisantika@yahoo.co.id")
                .url("https://s.id/hendisantika");
    }
}
