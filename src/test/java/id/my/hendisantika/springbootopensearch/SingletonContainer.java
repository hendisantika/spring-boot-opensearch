package id.my.hendisantika.springbootopensearch;

import org.testcontainers.containers.GenericContainer;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-opensearch
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/30/24
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 */
public class SingletonContainer {
    private static final GenericContainer<?> openSearchContainer = createOpenSearchContainer();
    private static final boolean isStarted = false;

    public static synchronized void startContainer() {
        if (!isStarted) {
            System.out.println("Starting container...");
            openSearchContainer.start();
            isStarted = true;
            System.out.println("Container started.");
        }
    }
}
