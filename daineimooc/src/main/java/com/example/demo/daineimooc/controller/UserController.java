package com.example.demo.daineimooc.controller;


import com.example.demo.daineimooc.entity.User;
import com.example.demo.daineimooc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/v1",tags = "CURD")
@RequestMapping(value = "/test")
public class UserController {

    /**
     * 注入UserService类
     */
    @Autowired
    private UserService userService;

    /**
     * 查询表中所有数据
     * @return
     */
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有数据",httpMethod = "GET")
    public List<User> getAll(){
        return userService.getAll();
    }

    /**
     * 根据id查询指定数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectUser",method = RequestMethod.GET)
    @ApiOperation(value = "查询某条数据",httpMethod = "GET")
    public User selectUser(@RequestParam Integer id){
        return userService.selectUser(id);
    }

    /**
     * 插入数据
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    @ApiOperation(value = "插入指定数据",httpMethod = "POST")
    public int insertUser(@RequestParam User user){
        return userService.insertUser(user);

    }

    /**
     * 根据id删除指定数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ApiOperation(value = "删除指定数据",httpMethod = "POST")
    public int deleteUser(@RequestParam Integer id){
        return userService.deleteUser(id);
    }

    /**
     * 更新某条数据
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "更新数据",httpMethod = "POST")
    public int updateUser(@RequestParam User user){
        return userService.updateUser(user);
    }





}
