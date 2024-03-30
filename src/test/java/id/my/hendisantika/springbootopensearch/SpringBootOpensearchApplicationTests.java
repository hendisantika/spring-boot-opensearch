package id.my.hendisantika.springbootopensearch;

import id.my.hendisantika.springbootopensearch.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootOpensearchApplicationTests {

    private static final GenericContainer<?> openSearchContainer = SingletonContainer.getInstance();

    @Autowired
    private BookService bookService;

    @Autowired
    private ElasticsearchOperations operations;

    @BeforeAll
    static void beforeAll() {
        SingletonContainer.startContainer();
        Assertions.assertTrue(openSearchContainer.isRunning());
    }

    @BeforeEach
    void testIsContainerRunning() {
        recreateIndex();
    }
}
