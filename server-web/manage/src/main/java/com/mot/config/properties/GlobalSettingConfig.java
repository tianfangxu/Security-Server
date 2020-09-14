package com.mot.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/globalSetting.properties")
public class GlobalSettingConfig {

    @Value("${server_name}")
    public String serverName;
    @Value("${JWT_ENCRYPT_HEADER}")
    public String jwtEncryptHeader;
}
