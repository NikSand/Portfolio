package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import java.util.List;
import java.util.Optional;

/**
 * Осуществление CRUD-операций с таблицей Пользователи.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    /**
     * Добавить нового пользователя в базу данных.
     * @param user Новый пользователь.
     */
    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Получить список всех пользователей.
     * @return Список всех пользователей.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Получить пользователя по ID.
     * @param id Идентификатор, по которому идет поиск пользователя в базе данных.
     * @return Пользователя по полученному ID.
     */
    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    /**
     * Получить пользователя по имени пользователя.
     * @param username Имя пользователя.
     * @return Пользователя по полученному имени пользователя.
     */
    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    /**
     * Удалить пользователя по ID.
     * @param id Идентификатор пользователя в базе данных.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Изменить информацию о пользователя.
     * @param user Новая информация о пользователе, которая заменит старые данные.
     */
    @Override
    @Transactional
    public void update(User user) {
        User oldUser = getById(user.getId());
        if (oldUser.getPassword().equals(user.getPassword()) || "".equals(user.getPassword())) {
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    /**
     * Получить пользователя по имени пользователя.
     * @param firstName Имя пользователя.
     * @return Контейнер Optional с пользователем.
     * @throws UsernameNotFoundException Пользователя с полученным именем нет в базе данных.
     */
    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Optional<User> userPrimary = getByUsername(firstName);
        if (userPrimary.isEmpty()) {
            throw new UsernameNotFoundException(firstName + " not found");
        }
        return userPrimary.get();
    }

    /**
     * Получить текущего авторизованного пользователя.
     * @return Авторизованный пользователь.
     */
    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
