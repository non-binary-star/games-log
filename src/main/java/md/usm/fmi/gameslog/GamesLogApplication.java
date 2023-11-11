package md.usm.fmi.gameslog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class GamesLogApplication {
    public static void main(final String[] args) {
        SpringApplication.run(GamesLogApplication.class, args);
    }
}
