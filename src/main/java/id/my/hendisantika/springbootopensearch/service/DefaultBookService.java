package id.my.hendisantika.springbootopensearch.service;

import id.my.hendisantika.springbootopensearch.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:07
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    private final RestHighLevelClient restHighLevelClient;
}
