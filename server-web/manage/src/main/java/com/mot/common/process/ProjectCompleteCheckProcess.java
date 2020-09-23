package com.mot.common.process;

import com.mot.common.constant.URLConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author tianfx
 * @Date 2020/9/21
 * 项目启动后 对一些规范校验
 */
public class ProjectCompleteCheckProcess implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        fileUrlCheck();
    }

    /**
     * 校验controller 层注解上的路径地址一定要是从@URLConstant 类中获取
     */
    void fileUrlCheck(){
        Map<String, Object> controllerMap = applicationContext.getBeansWithAnnotation(RestController.class);
        List<String> list = URLConstant.getInstance().urlsAll();
        controllerMap.forEach( (s, o) -> {
            String className = o.getClass().getPackage().getName() + "." + firstUpperCase(s);
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    if (annotation!=null && annotation.value() !=null && annotation.value().length >0){
                        String url = annotation.value()[0];
                        if (!list.contains("/"+url)){
                            logger.warn("[警告] 控制层{}的路径{}需要注册到#URLConstant类中,并使用@FiledUrlAnnotation添加相关权限！！！",s,url);
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public String firstUpperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
