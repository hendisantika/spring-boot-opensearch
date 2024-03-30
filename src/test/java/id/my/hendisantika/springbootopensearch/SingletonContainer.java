package id.my.hendisantika.springbootopensearch;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.net.HttpURLConnection;
import java.nio.file.Paths;
import java.time.Duration;

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
    private static boolean isStarted = false;

    public static synchronized void startContainer() {
        if (!isStarted) {
            System.out.println("Starting container...");
            openSearchContainer.start();
            isStarted = true;
            System.out.println("Container started.");
        }
    }

    public static GenericContainer<?> getInstance() {
        return openSearchContainer;
    }

    private static GenericContainer<?> createOpenSearchContainer() {
        ImageFromDockerfile image = new ImageFromDockerfile()
                .withDockerfile(Paths.get("develop-scripts/Dockerfile"));
        return new GenericContainer<>(image)
                .withEnv("discovery.type", "single-node")
                .withEnv("DISABLE_SECURITY_PLUGIN", "true")
                .withEnv("OPENSEARCH_JAVA_OPTS", "-Xms512m -Xmx512m")
                .withExposedPorts(9200, 9600)
                .waitingFor(
                        new HttpWaitStrategy()
                                .forPort(9200)
                                .forStatusCodeMatching(response -> response == HttpURLConnection.HTTP_OK ||
                                        response == HttpURLConnection.HTTP_UNAUTHORIZED)
                                .withStartupTimeout(Duration.ofMinutes(2))
                );
    }
}
