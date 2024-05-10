package br.com.demo.supermarket.presenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan("br.com.demo.supermarket")
@EntityScan("br.com.demo.supermarket")
@EnableJpaRepositories("br.com.demo.supermarket.infrastructure.repository")
public class SupermarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class, args);
    }

}
