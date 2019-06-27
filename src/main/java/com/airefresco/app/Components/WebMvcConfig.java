package com.airefresco.app.Components;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("").setViewName("portal");
        registry.addViewController("/").setViewName("portal");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/certificados").setViewName("certificados");
        registry.addViewController("/administrador").setViewName("administrador");
        registry.addViewController("/portal").setViewName("portal");
        registry.addViewController("/home").setViewName("home");
        /**section for administrator area*/
        registry.addViewController("/registro").setViewName("registro");
        registry.addViewController("/controlUsuarios").setViewName("controlUsuarios");
        registry.addViewController("/registro").setViewName("registro");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/supportSources/**").addResourceLocations("classpath:/static/supportSources/");
    	registry.addResourceHandler("/script/**").addResourceLocations("classpath:/static/script/");
    }
}
