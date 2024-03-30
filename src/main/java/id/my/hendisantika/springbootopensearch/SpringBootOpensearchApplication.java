package id.my.hendisantika.springbootopensearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class SpringBootOpensearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOpensearchApplication.class, args);
    }

}
