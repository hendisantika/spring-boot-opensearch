package id.my.hendisantika.springbootopensearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

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
@Document(indexName = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    private String title;

    private int publicationYear;

    private String authorName;

    private String isbn;
}
