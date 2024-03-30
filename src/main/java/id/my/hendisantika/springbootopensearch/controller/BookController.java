package id.my.hendisantika.springbootopensearch.controller;

import id.my.hendisantika.springbootopensearch.model.Book;
import id.my.hendisantika.springbootopensearch.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
