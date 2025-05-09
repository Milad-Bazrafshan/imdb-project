package org.clw.imdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"org.clw.imdb.services"})
public class ImdbProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImdbProjectApplication.class, args);
    }

}
