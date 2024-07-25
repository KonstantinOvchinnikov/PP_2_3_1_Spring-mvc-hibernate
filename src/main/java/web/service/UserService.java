package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> showAllUsers();
    void saveUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User showUser(int id);
}
