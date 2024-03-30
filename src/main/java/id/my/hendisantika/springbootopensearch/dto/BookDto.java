package id.my.hendisantika.springbootopensearch.dto;

import id.my.hendisantika.springbootopensearch.metadata.PublicationYear;
import id.my.hendisantika.springbootopensearch.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:11
 * To change this template use File | Settings | File Templates.
 */
public class BookDto {
    @NotBlank
    private String title;

    @Positive
    @PublicationYear
    private Integer publicationYear;

    @NotBlank
    private String authorName;

    @NotBlank
    private String isbn;

    static Book transform(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.title);
        book.setPublicationYear(bookDto.publicationYear);
        book.setAuthorName(bookDto.authorName);
        book.setIsbn(bookDto.isbn);
        return book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
