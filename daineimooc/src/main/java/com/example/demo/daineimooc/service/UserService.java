package com.example.demo.daineimooc.service;

import com.example.demo.daineimooc.entity.User;
import io.swagger.models.auth.In;

import java.util.List;

public interface UserService {


    List<User> getAll();

    User selectUser(Integer id);

    int insertUser(User user);


    int deleteUser(Integer id);


    int updateUser(User user);




}
