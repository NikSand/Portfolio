package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;

/**
 * REST-контролер для запросов пользователя с ролью admin.
 */
@RestController
@RequestMapping("api/admins")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    /**
     * Получить список со всеми ролями пользователя.
     * @return Список с ролями пользователя.
     */
    @GetMapping("/getRoles")
    public List<Role> getRoles() {
        return roleService.getListRoles();
    }

    /**
     * Получить список со всеми пользователями в базе данных.
     * @return Список со всеми пользователями в базе данных.
     */
    @GetMapping
    public ResponseEntity<List<User>> showUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Получить пользователя по его ID.
     * @param id Идентификатор пользователя в базе данных.
     * @return Пользователя по полученному ID.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        return new ResponseEntity<> (userService.getById(id), HttpStatus.OK);
    }

    /**
     * Получит информацию об авторизованном пользователе.
     * @return Текущего авторизованного пользователя.
     */
    @GetMapping("/userAuth")
    public ResponseEntity<User> showAuthUser() {
        return new ResponseEntity<> (userService.getCurrentUser(), HttpStatus.OK);
    }

    /**
     * Добавить нового пользователя в базу данных.
     * @param user Новый пользователь.
     * @return Статус ответа CREATED от сервера.
     */
    @PostMapping("/newAddUser")
    public ResponseEntity<Void> saveNewUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Удалить пользователя по его ID.
     * @param id Идентификатор пользователя в базе данных.
     * @return Статус ответа OK от сервера.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Изменить пользователя по его ID.
     * @param user Новая информация о пользователе, которая заменит старые данные.
     * @param id Идентификатор пользователя в базе данных.
     * @return Статус ответа OK от сервера.
     */
    @PatchMapping("/users/{id}")
    public ResponseEntity<Void> userSaveEdit(@RequestBody User user, @PathVariable Long id ) {
        user.setId(id);
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}