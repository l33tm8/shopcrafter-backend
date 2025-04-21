package ru.ilya.shopcrafterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"ru.ilya"})
@EnableJpaRepositories(basePackages = {
        "ru.ilya.shopcraftercore.repository",
})
@EntityScan(basePackages = {
        "ru.ilya.shopcraftercore.entity",
        "ru.ilya.shopcrafterapi.entity"
})
public class ShopcrafterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopcrafterApplication.class, args);
    }

}
