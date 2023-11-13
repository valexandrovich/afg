package ua.com.valexa.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"ua.com.valexa.db.model"})
@EnableJpaRepositories(basePackages = {"ua.com.valexa.db"})
@ComponentScan(basePackages = {"ua.com.valexa.db", "ua.com.valexa.sandbox"})
public class SandboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }

}
