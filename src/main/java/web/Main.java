package web;

import web.dao.UserDaoImp;
import web.service.UserService;
import web.service.UserServiceImp;

public class Main {
    public static void main(String... args) {
        UserService userService = new UserServiceImp(new UserDaoImp());
        userService.showAllUsers().forEach(a -> System.out.println(a.getName() + a.getSurname()));
    }
}
