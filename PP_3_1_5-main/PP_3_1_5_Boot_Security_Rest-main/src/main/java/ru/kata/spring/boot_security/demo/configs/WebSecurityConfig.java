package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.service.CustomUserDetailService;

/**
 * Аутентификация и авторизация пользователя через SpringBoot.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;
    private final CustomUserDetailService customUserDetailService;


    public WebSecurityConfig(SuccessUserHandler successUserHandler, CustomUserDetailService customUserDetailService) {
        this.successUserHandler = successUserHandler;
        this.customUserDetailService = customUserDetailService;
    }

    /**
     * Конфигурация фильтров SpringBoot.
     * @param http Запрос на аутентификацию от пользователя.
     * @throws Exception Некорректные данные при аутентификации пользователя.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String admin = "ADMIN";
        String user = "USER";
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admins/**").hasRole(admin)
                .antMatchers("/user/**").hasAnyRole(user, admin)
                .antMatchers("/api/admins/**").hasRole(admin)
                .antMatchers("/api/user/**").hasAnyRole(user, admin)
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler).permitAll()
                .and()
                .logout().permitAll();
    }

    /**
     * BCrypt декодер пароля пользователя.
     * @return Декодированный пароль пользователя.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Получить информацию о пользователе, прошедшем аутентификацию.
     * @return Информацию об пользователе, успешно прошедшем аутентификацию.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailService);
        return authenticationProvider;
    }
}
