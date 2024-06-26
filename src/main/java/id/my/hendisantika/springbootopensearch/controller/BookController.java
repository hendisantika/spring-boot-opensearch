package id.my.hendisantika.springbootopensearch.controller;

import id.my.hendisantika.springbootopensearch.dto.BookDto;
import id.my.hendisantika.springbootopensearch.exception.BookNotFoundException;
import id.my.hendisantika.springbootopensearch.exception.DuplicateIsbnException;
import id.my.hendisantika.springbootopensearch.model.Book;
import id.my.hendisantika.springbootopensearch.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:10
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Book createBook(@Valid @RequestBody BookDto book) throws DuplicateIsbnException {
        return bookService.create(BookDto.transform(book));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        return bookService.getByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("The given isbn is invalid"));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/query")
    public List<Book> getBooksByAuthorAndTitle(@RequestParam(value = "title") String title, @RequestParam(value = "author") String author) {
        return bookService.findByTitleAndAuthor(title, author);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody BookDto book) throws BookNotFoundException {
        return bookService.update(id, BookDto.transform(book));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }
}
