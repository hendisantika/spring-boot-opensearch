package id.my.hendisantika.springbootopensearch.metadata;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:03
 * To change this template use File | Settings | File Templates.
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, PARAMETER})
@Constraint(validatedBy = PublicationYearValidator.class)
public @interface PublicationYear {

    String message() default "Publication year cannot be future year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
