package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

/**
 * Получение информации о пользователе для авторизации в программе.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Поиск и получение пользователя из базы данных по имени пользователя.
     * @param username Имя пользователя, по которому идет поиск пользователя в базе данных.
     * @return Пользователя с совпадающим именем.
     * @throws UsernameNotFoundException Если в базе данных нет пользователя с искомым именем.
     */
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndRoles(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
