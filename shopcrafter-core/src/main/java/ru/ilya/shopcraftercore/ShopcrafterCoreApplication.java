package ru.ilya.shopcraftercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru.ilya.shopcraftercore", "ru.ilya.shopcraftergoods"})
public class ShopcrafterCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopcrafterCoreApplication.class, args);
    }

}
