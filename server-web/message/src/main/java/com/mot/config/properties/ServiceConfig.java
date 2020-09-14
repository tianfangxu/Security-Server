package com.mot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("classpath:config/netty-handler-service-config.properties")
@ConfigurationProperties(prefix = "message.service")
@Data
public class ServiceConfig {

    public List<Nods> nods;

    public static class Nods {
        private String type;
        private String beanName;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }
    }

}
