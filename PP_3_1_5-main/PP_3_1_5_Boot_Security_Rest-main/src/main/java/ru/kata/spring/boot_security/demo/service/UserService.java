package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entities.User;
import java.util.List;
import java.util.Optional;

/**
 * Осуществление CRUD-операций с таблицей Пользователи.
 */
public interface UserService {

    /**
     * Добавить нового пользователя в базу данных.
     * @param user Новый пользователь.
     */
    void add(User user);

    /**
     * Получить список всех пользователей.
     * @return Список всех пользователей.
     */
    List<User> getAllUsers();

    /**
     * Удалить пользователя по ID.
     * @param id Идентификатор пользователя в базе данных.
     */
    void delete(Long id);

    /**
     * Изменить информацию о пользователя.
     * @param user Новая информация о пользователе, которая заменит старые данные.
     */
    void update(User user);

    /**
     * Получить пользователя по ID.
     * @param id Идентификатор, по которому идет поиск пользователя в базе данных.
     * @return Пользователя по полученному ID.
     */
    User getById(Long id);

    /**
     * Получить пользователя по имени пользователя.
     * @param userName Имя пользователя.
     * @return Пользователя по полученному имени пользователя.
     */
    Optional<User> getByUsername(String userName);

    /**
     * Получить пользователя по имени пользователя.
     * @param firstName Имя пользователя.
     * @return Контейнер Optional с пользователем.
     * @throws UsernameNotFoundException Пользователя с полученным именем нет в базе данных.
     */
    UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException;

    /**
     * Получить текущего авторизованного пользователя.
     * @return Авторизованный пользователь.
     */
    User getCurrentUser();
}
