package id.my.hendisantika.springbootopensearch.repository;

import id.my.hendisantika.springbootopensearch.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:05
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findByAuthorName(String authorName);

    List<Book> findByTitleAndAuthorName(String title, String authorName);

    Optional<Book> findByIsbn(String isbn);
}
