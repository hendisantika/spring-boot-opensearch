package id.my.hendisantika.springbootopensearch;

import id.my.hendisantika.springbootopensearch.exception.DuplicateIsbnException;
import id.my.hendisantika.springbootopensearch.model.Book;
import id.my.hendisantika.springbootopensearch.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void testGetBookByIsbn() throws DuplicateIsbnException {
        bookService.create(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023"));
        Optional<Book> result = bookService.getByIsbn("978-0345816023");
        assertTrue(result.isPresent());
        Book createdBook = result.get();
        assertNotNull(createdBook);
        assertEquals("12 rules for life", createdBook.getTitle());
        assertEquals("Jordan Peterson", createdBook.getAuthorName());
        assertEquals(2018, createdBook.getPublicationYear());
        assertEquals("978-0345816023", createdBook.getIsbn());
    }

    @Test
    void testGetAllBooks() throws DuplicateIsbnException {
        bookService.create(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023"));
        bookService.create(createBook("The Cathedral and the Bazaar", "Eric Raymond", 1999, "9780596106386"));
        List<Book> books = bookService.getAll();

        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    void testFindByAuthor() throws DuplicateIsbnException {
        bookService.create(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023"));
        bookService.create(createBook("Maps of Meaning", "Jordan Peterson", 1999, "9781280407253"));

        List<Book> books = bookService.findByAuthor("Jordan Peterson");

        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    void testFindByTitleAndAuthor() throws DuplicateIsbnException {
        bookService.create(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023"));
        bookService.create(createBook("Rules or not rules?", "Jordan Miller", 2010, "978128000000"));
        bookService.create(createBook("Poor economy", "Jordan Miller", 2006, "9781280789000"));
        bookService.create(createBook("The Cathedral and the Bazaar", "Eric Raymond", 1999, "9780596106386"));

        List<Book> books = bookService.findByTitleAndAuthor("rules", "jordan");

        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    void testCreateBook() throws DuplicateIsbnException {
        Book createdBook = bookService.create(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023"));
        assertNotNull(createdBook);
        assertNotNull(createdBook.getId());
        assertEquals("12 rules for life", createdBook.getTitle());
        assertEquals("Jordan Peterson", createdBook.getAuthorName());
        assertEquals(2018, createdBook.getPublicationYear());
        assertEquals("978-0345816023", createdBook.getIsbn());
    }
}
