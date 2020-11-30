package com.mot.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("classpath:config/globalSetting.properties")
public class GlobalSettingConfig {

    @Value("${server_name}")
    public String serverName;
    @Value("${file_static_url}")
    public String fileStaticUrl;
    @Value("#{'${file_static_path}'.split(',')}")
    public String[] fileStaticPaths;
    @Value("${root_file_path}")
    public String rootFilePath;

}
