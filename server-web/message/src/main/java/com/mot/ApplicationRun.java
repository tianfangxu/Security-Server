package com.mot;

import com.mot.common.constant.RabbitMqConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRun {

    public static void main(String[] args) {
        initEnvironment();
        SpringApplication.run(ApplicationRun.class);
    }

    /**
     * 项目启动前初始化一些环境信息
     *  server.message.uid : 当前服务绑定的队列名称
     */
    private static void initEnvironment() {
        System.out.println(RabbitMqConstant.routerKey);
        System.setProperty("server.message.uid", RabbitMqConstant.routerKey);

    }
}
