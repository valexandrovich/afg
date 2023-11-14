package ua.com.valexa.enricher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan(basePackages = {"ua.com.valexa.db.model"})
@EnableJpaRepositories(basePackages = {"ua.com.valexa.db.repository"})
@ComponentScan(basePackages = {"ua.com.valexa.db", "ua.com.valexa.enricher"})
@EnableAsync
public class EnricherApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnricherApplication.class, args);
    }

}
