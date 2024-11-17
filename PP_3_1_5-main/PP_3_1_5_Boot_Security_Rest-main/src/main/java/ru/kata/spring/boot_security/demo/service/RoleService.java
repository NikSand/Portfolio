package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entities.Role;
import java.util.List;

/**
 * Получение списка ролей пользователей из базы данных.
 */
public interface RoleService {

    /**
     * Возвращает список всех ролей пользователей из базы данных.
     * @return Список ролей пользователей.
     */
    List<Role> getListRoles();
}
