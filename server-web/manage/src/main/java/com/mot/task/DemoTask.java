package com.mot.task;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author tianfx
 * @date 2021/5/18 3:37 下午
 * ** XxlJobHelper
 */
@Component
public class DemoTask {

    @XxlJob("demo")
    public void demo(){
        String jobParam = XxlJobHelper.getJobParam();
        System.out.println(jobParam);
        System.out.println(1);
        XxlJobHelper.handleSuccess("success-tfx");
    }

    @XxlJob("demo1")
    public void demo1(){
        int index = XxlJobHelper.getShardIndex();
        int total = XxlJobHelper.getShardTotal();
        System.out.println("current"+index);
        System.out.println("total"+total);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(3<<3);
    }
}
