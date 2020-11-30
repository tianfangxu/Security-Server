package com.mot.config.configuration;

import com.mot.config.properties.GlobalSettingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源设置
 */
@Configuration
public class StaticResourcesConfiguration implements WebMvcConfigurer {

    @Autowired
    GlobalSettingConfig globalSettingConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(globalSettingConfig.fileStaticUrl)
                .addResourceLocations(globalSettingConfig.fileStaticPaths);
    }
}
