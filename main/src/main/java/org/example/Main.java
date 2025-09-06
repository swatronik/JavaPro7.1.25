package org.example;

import org.example.entity.User;
import org.example.service.UserService;
import org.example.utils.InitializerDB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class.getPackage().getName());
        InitializerDB initializerDB = applicationContext.getBean(InitializerDB.class);
        initializerDB.initDB();
        UserService bean = applicationContext.getBean(UserService.class);
        Long user1 = bean.insertUser("petr");
        Long user2 = bean.insertUser("oleg");
        Long user3 = bean.insertUser("vadim");

        bean.getAllUsers().forEach(System.out::println);
        System.out.println();

        bean.updateUser(user2, "ivan");
        User user = bean.getUser(user2);
        System.out.println(user);
        System.out.println();

        bean.deleteUser(user1);
        bean.deleteUser(user2);
        bean.deleteUser(user3);
        bean.getAllUsers().forEach(System.out::println);
    }
}