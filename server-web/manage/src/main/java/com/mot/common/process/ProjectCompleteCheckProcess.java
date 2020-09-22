package com.mot.common.process;

import org.springframework.boot.CommandLineRunner;

/**
 * @Author tianfx
 * @Date 2020/9/21
 * 项目启动后 对一些规范校验
 */

public class ProjectCompleteCheckProcess implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        fileUrlCheck();
    }

    /**
     * 校验controller 层注解上的路径地址一定要是从@URLConstant 类中获取
     */
    void fileUrlCheck(){


    }
}
