package com.example.demo.daineimooc.service;

import com.example.demo.daineimooc.entity.User;
import sun.nio.cs.US_ASCII;

import java.util.List;

public interface UserService {



    List<User> getAll();

    List<User> selectUser(Integer id);

    int insertUser(User user);


    int deleteUser(Integer id);


    int updateUser(User user);




}
