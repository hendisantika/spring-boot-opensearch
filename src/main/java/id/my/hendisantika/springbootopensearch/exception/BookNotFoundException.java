package id.my.hendisantika.springbootopensearch.exception;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:06
 * To change this template use File | Settings | File Templates.
 */
public class BookNotFoundException extends Exception {

    public BookNotFoundException(String message) {
        super(message);
    }
}
