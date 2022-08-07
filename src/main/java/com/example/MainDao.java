package com.example;

import com.example.entity.User;
import com.example.entity.UserDao;

public class MainDao {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
//        User user = new User();
//        user.setUserName("Yoda");
//        user.setEmail("yodzi10@gmail.com");
//        user.setPassword("Moc123!");
//        userDao.create(user);
//        User user1 = new User();
//        user1.setUserName("ObiWan");
//        user1.setEmail("adres@email.com");
//        user1.setPassword("haslo12!");
//        userDao.create(user1);
//        userDao.findAll().stream()
//                .forEach(System.out::println);
//        User userToUpdate =userDao.read(2);
//        userToUpdate.setUserName("Windu");
//        userToUpdate.setEmail("windu@op.pl");
//        userDao.update(userToUpdate);
        userDao.findAll().stream()
                .forEach(System.out::println);
        userDao.delete(2);
        userDao.findAll().stream()
                .forEach(System.out::println);


    }
}
