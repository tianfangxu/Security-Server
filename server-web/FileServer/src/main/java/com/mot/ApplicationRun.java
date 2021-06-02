package com.mot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ApplicationRun{

    //alpha beta
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class);
    }

}
