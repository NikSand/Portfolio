package springsecurity.springbootsecurity.services;

import springsecurity.springbootsecurity.model.User;
import java.util.List;

public interface UserService  {

    List<User> findAll();

    User findByUsername(String email);

    boolean deleteUser(Long id);

    boolean saveUser(User user);

    void updateUser(User user, Long id);

}