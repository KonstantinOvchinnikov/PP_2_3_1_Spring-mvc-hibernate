package web.dao;


import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImp implements UserDao {
    private static int USER_ID ;
    private List<User> users;
    {
        users = new ArrayList<>();
        users.add(new User(++USER_ID,"name1", "surname1", 35, "email1"));
        users.add(new User(++USER_ID,"name2", "surname2", 37, "email2"));
        users.add(new User(++USER_ID, "name3", "surname3", 25, "email3"));
        users.add(new User(++USER_ID,"name4", "surname4", 39, "email4"));
    }

    @Override
    public List<User> showAllUsers() {
        return users;
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == 0) {
            user.setId(++USER_ID);
            users.add(user);
        } else {
            users.remove(users.get(user.getId()));
            users.add(user);
        }
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }

    @Override
    public void updateUser(User user) {
        User userUpdate = showUser(user.getId());
        userUpdate.setName(user.getName());
        userUpdate.setSurname(user.getSurname());
        userUpdate.setAge(user.getAge());
        userUpdate.setEmail(user.getEmail());
    }

    @Override
    public User showUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElseGet(null);
    }
}
