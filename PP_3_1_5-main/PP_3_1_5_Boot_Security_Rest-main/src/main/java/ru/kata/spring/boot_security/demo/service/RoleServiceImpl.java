package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import java.util.List;

/**
 * Получение списка ролей пользователей из базы данных.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Возвращает список всех ролей пользователей из базы данных.
     * @return Список ролей пользователей.
     */
    @Override
    public List<Role> getListRoles() {
        return roleRepository.findAll();
    }
}
