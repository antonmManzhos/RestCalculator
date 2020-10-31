package home.org.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "home.org")
@EntityScan(basePackages = "home.org.model")
@EnableJpaRepositories(basePackages = "home.org.repository")
public class Runner {
        public static void main(String[] args) {
            SpringApplication.run(Runner.class, args);
        }
}
