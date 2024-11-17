package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация SPRING MVC.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * Добавить новый ViewController для установки перехода на базовые страницы для пользователя и админа.
     * @param registry ViewControllerRegistry.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/admins").setViewName("admin");
    }

    /**
     * Добавить хранилище ресурсов со страницами сайта.
     * @param registry ResourceHandlerRegistry.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/js/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/js/");
    }
}
