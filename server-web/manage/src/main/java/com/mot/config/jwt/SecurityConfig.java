package com.mot.config.jwt;

import com.mot.common.annotation.FiledUrlAnnotation;
import com.mot.common.constant.URLConstant;
import com.mot.config.properties.GlobalSettingConfig;
import com.mot.filter.JwtAuthenticationTokenFilter;
import com.mot.service.impl.AuthDetailsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthDetailsUserService authDetailsUserService;

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    GlobalSettingConfig globalSettingConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //开启跨站攻击防御，首次XSRF-TOKEN放在cookie中,并允许访问cookie，后面每次请求需要在请求头中加入X-XSRF-TOKEN
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(getWhiteList())
                //跨域访问
                .and().cors()
                //判断是否登录拦截器
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //权限判断方法
                .authorizeRequests()
                .antMatchers(getWhiteList()).permitAll()
                .anyRequest().access("@userPermissionService.hasPermission(request,authentication)")
                //设置无session状态
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
    }

    private String[] getWhiteList() {
        Field[] fields = URLConstant.class.getFields();
        ArrayList<String> list = new ArrayList<>();
        for (Field field : fields) {
            FiledUrlAnnotation annotation = field.getAnnotation(FiledUrlAnnotation.class);
            if (annotation != null && !annotation.hasPermit()){
                field.setAccessible(true);
                try {
                    Object o = field.get(null);
                    list.add("/"+String.valueOf(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authDetailsUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://192.168.1.47:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("X-XSRF-TOKEN","Content-Type",globalSettingConfig.jwtEncryptHeader));
        configuration.setAllowCredentials(true);
        configuration.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public static void main(String[] args) {

    }
}
