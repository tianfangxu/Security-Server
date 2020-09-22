package com.mot;

import com.mot.common.process.ProjectCompleteCheckProcess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mot.mapper")
public class ApplicationRun extends ProjectCompleteCheckProcess {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class);
    }

}
