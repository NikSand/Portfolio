package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

/**
 * REST-контролер для запросов пользователя с ролью user.
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получить информацию о текущем авторизованном пользователе.
     * @return Информация о текущем авторизованном пользователе.
     */
    @GetMapping
    public ResponseEntity<User> showUser() {
        return new ResponseEntity<> (userService.getCurrentUser(), HttpStatus.OK);
    }

}
