package com.xbd.note.shopping.cache;

import com.xbd.note.shopping.cache.filter.HystrixRequestContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingCacheApplication {

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new HystrixRequestContextFilter());
//        registrationBean.addUrlPatterns("/*");
//        return  registrationBean;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCacheApplication.class, args);
    }

}
