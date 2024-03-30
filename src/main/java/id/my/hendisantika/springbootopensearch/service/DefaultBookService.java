package id.my.hendisantika.springbootopensearch.service;

import id.my.hendisantika.springbootopensearch.exception.DuplicateIsbnException;
import id.my.hendisantika.springbootopensearch.model.Book;
import id.my.hendisantika.springbootopensearch.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Book> getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll()
                .forEach(books::add);
        return books;
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthorName(title, author);
    }

    //    @Override
//    public List<Book> findByTitleAndAuthor(String title, String author) {
//        var criteria = QueryBuilders.boolQuery()
//                .must(QueryBuilders.matchQuery("authorName", author))
//                .must(QueryBuilders.matchQuery("title", title));
//
//        SearchRequest searchRequest = new SearchRequest("book-index");
//        searchRequest.source().query(criteria);
//
//        try {
//            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//            return Arrays.stream(response.getHits().getHits())
//                    .map(hit -> new ObjectMapper().convertValue(hit.getSourceAsMap(), Book.class))
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new RuntimeException("Error executing search", e);
//        }
//    }

    @Override
    public Book create(Book book) throws DuplicateIsbnException {
        if (getByIsbn(book.getIsbn()).isEmpty()) {
            return bookRepository.save(book);
        }
        throw new DuplicateIsbnException(String.format("The provided ISBN: %s already exists. Use update instead!", book.getIsbn()));
    }
}
