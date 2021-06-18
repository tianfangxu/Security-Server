package com.mot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tianfangxu
 */
@SpringBootApplication
@EnableScheduling
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class);
    }
}
